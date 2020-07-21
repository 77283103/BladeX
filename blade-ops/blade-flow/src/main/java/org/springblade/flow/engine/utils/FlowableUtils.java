package org.springblade.flow.engine.utils;

import com.google.common.collect.Sets;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
import org.springblade.flow.engine.constant.FlowEngineConstant;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlowableUtils {

	public static boolean isReachable(Process process, FlowNode sourceElement, FlowNode targetElement,
									  Set<String> visitedElements) {
		if (sourceElement instanceof StartEvent && isInEventSubprocess(sourceElement)) {
			return false;
		}
		if (sourceElement.getOutgoingFlows().size() == 0) {
			visitedElements.add(sourceElement.getId());
			FlowElementsContainer parentElement = process.findParent(sourceElement);
			if (parentElement instanceof SubProcess) {
				sourceElement = (SubProcess) parentElement;
				// by zjm begin
				// 子流程的结束节点，若目标节点在该子流程中，说明无法到达，返回false
				if (((SubProcess) sourceElement).getFlowElement(targetElement.getId()) != null) {
					return false;
				}
			} else {
				return false;
			}
		}
		if (sourceElement.getId().equals(targetElement.getId())) {
			return true;
		}
		visitedElements.add(sourceElement.getId());
		// 当前节点能够到达子流程，且目标节点在子流程中，说明可以到达，返回true
		if (sourceElement instanceof SubProcess
			&& ((SubProcess) sourceElement).getFlowElement(targetElement.getId()) != null) {
			return true;
		}
		List<SequenceFlow> sequenceFlows = sourceElement.getOutgoingFlows();
		if (sequenceFlows != null && sequenceFlows.size() > 0) {
			for (SequenceFlow sequenceFlow : sequenceFlows) {
				String targetRef = sequenceFlow.getTargetRef();
				FlowNode sequenceFlowTarget = (FlowNode) process.getFlowElement(targetRef, true);
				if (sequenceFlowTarget != null && !visitedElements.contains(sequenceFlowTarget.getId())) {
					boolean reachable = isReachable(process, sequenceFlowTarget, targetElement, visitedElements);
					if (reachable) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean isReachable(Process process, FlowNode sourceElement, FlowNode targetElement) {
		return isReachable(process, sourceElement, targetElement, Sets.newHashSet());
	}

	protected static boolean isInEventSubprocess(FlowNode flowNode) {
		FlowElementsContainer flowElementsContainer = flowNode.getParentContainer();
		while (flowElementsContainer != null) {
			if (flowElementsContainer instanceof EventSubProcess) {
				return true;
			}
			if (flowElementsContainer instanceof FlowElement) {
				flowElementsContainer = ((FlowElement) flowElementsContainer).getParentContainer();
			} else {
				flowElementsContainer = null;
			}
		}
		return false;
	}

	public static String getParentExecutionIdFromParentIds(ExecutionEntity execution, Set<String> parentExecutionIds) {
		ExecutionEntity taskParentExecution = execution.getParent();
		String realParentExecutionId = null;
		while (taskParentExecution != null) {
			if (parentExecutionIds.contains(taskParentExecution.getId())) {
				realParentExecutionId = taskParentExecution.getId();
				break;
			}
			taskParentExecution = taskParentExecution.getParent();
		}
		if (realParentExecutionId == null || realParentExecutionId.length() == 0) {
			throw new FlowableException("Parent execution could not be found with executionId id " + execution.getId());
		}
		return realParentExecutionId;
	}

	public static Set<String> getParentExecutionIdsByActivityId(List<ExecutionEntity> executions, String activityId) {
		List<ExecutionEntity> activityIdExecutions = executions.stream()
			.filter(e -> activityId.equals(e.getActivityId())).collect(Collectors.toList());
		if (activityIdExecutions.isEmpty()) {
			throw new FlowableException("Active execution could not be found with activity id " + activityId);
		}
		// check for a multi instance root execution
		ExecutionEntity miExecution = null;
		boolean isInsideMultiInstance = false;
		for (ExecutionEntity possibleMiExecution : activityIdExecutions) {
			if (possibleMiExecution.isMultiInstanceRoot()) {
				miExecution = possibleMiExecution;
				isInsideMultiInstance = true;
				break;
			}
			if (isExecutionInsideMultiInstance(possibleMiExecution)) {
				isInsideMultiInstance = true;
			}
		}
		Set<String> parentExecutionIds = new HashSet<>();
		if (isInsideMultiInstance) {
			Stream<ExecutionEntity> executionEntitiesStream = activityIdExecutions.stream();
			if (miExecution != null) {
				executionEntitiesStream = executionEntitiesStream.filter(ExecutionEntity::isMultiInstanceRoot);
			}
			executionEntitiesStream.forEach(childExecution -> {
				parentExecutionIds.add(childExecution.getParentId());
			});
		} else {
			ExecutionEntity execution = activityIdExecutions.iterator().next();
			parentExecutionIds.add(execution.getParentId());
		}
		return parentExecutionIds;
	}

	public static String[] getSourceAndTargetRealActivityId(FlowNode sourceFlowElement, FlowNode targetFlowElement) {
		// 实际应操作的当前节点ID
		String sourceRealActivityId = sourceFlowElement.getId();
		// 实际应操作的目标节点ID
		String targetRealActivityId = targetFlowElement.getId();
		List<String> sourceParentProcesss = FlowableUtils.getParentProcessIds(sourceFlowElement);
		List<String> targetParentProcesss = FlowableUtils.getParentProcessIds(targetFlowElement);
		int diffParentLevel = getDiffLevel(sourceParentProcesss, targetParentProcesss);
		if (diffParentLevel != -1) {
			sourceRealActivityId = sourceParentProcesss.size() == diffParentLevel ? sourceRealActivityId
				: sourceParentProcesss.get(diffParentLevel);
			targetRealActivityId = targetParentProcesss.size() == diffParentLevel ? targetRealActivityId
				: targetParentProcesss.get(diffParentLevel);
		}
		return new String[]{sourceRealActivityId, targetRealActivityId};
	}

	public static Map<String, Set<String>> getSpecialGatewayElements(FlowElementsContainer container) {
		return getSpecialGatewayElements(container, null);
	}

	public static Map<String, Set<String>> getSpecialGatewayElements(FlowElementsContainer container,
																	 Map<String, Set<String>> specialGatewayElements) {
		if (specialGatewayElements == null) {
			specialGatewayElements = new HashMap<>(16);
		}
		Collection<FlowElement> flowelements = container.getFlowElements();
		for (FlowElement flowElement : flowelements) {
			boolean isBeginSpecialGateway = flowElement.getId().endsWith(FlowEngineConstant.SPECIAL_GATEWAY_BEGIN_SUFFIX)
				&& (flowElement instanceof ParallelGateway || flowElement instanceof InclusiveGateway
				|| flowElement instanceof ComplexGateway);
			if (isBeginSpecialGateway) {
				String gatewayBeginRealId = flowElement.getId();
				String gatewayId = gatewayBeginRealId.substring(0, gatewayBeginRealId.length() - 6);
				Set<String> gatewayIdContainFlowelements = specialGatewayElements.computeIfAbsent(gatewayId,
					k -> new HashSet<>());
				findElementsBetweenSpecialGateway(flowElement, gatewayId + FlowEngineConstant.SPECIAL_GATEWAY_END_SUFFIX,
					gatewayIdContainFlowelements);
			} else if (flowElement instanceof SubProcess) {
				getSpecialGatewayElements((SubProcess) flowElement, specialGatewayElements);
			}
		}

		// 外层到里层排序
		Map<String, Set<String>> specialGatewayNodesSort = new LinkedHashMap<>();
		specialGatewayElements.entrySet().stream().sorted((o1, o2) -> o2.getValue().size() - o1.getValue().size())
			.forEach(entry -> specialGatewayNodesSort.put(entry.getKey(), entry.getValue()));

		return specialGatewayNodesSort;
	}

	public static void findElementsBetweenSpecialGateway(FlowElement specialGatewayBegin, String specialGatewayEndId,
														 Set<String> elements) {
		elements.add(specialGatewayBegin.getId());
		List<SequenceFlow> sequenceFlows = ((FlowNode) specialGatewayBegin).getOutgoingFlows();
		if (sequenceFlows != null && sequenceFlows.size() > 0) {
			for (SequenceFlow sequenceFlow : sequenceFlows) {
				FlowElement targetFlowElement = sequenceFlow.getTargetFlowElement();
				String targetFlowElementId = targetFlowElement.getId();
				elements.add(specialGatewayEndId);
				if (targetFlowElementId.equals(specialGatewayEndId)) {
					continue;
				} else {
					findElementsBetweenSpecialGateway(targetFlowElement, specialGatewayEndId, elements);
				}
			}
		}
	}

	public static List<String> getParentProcessIds(FlowNode flowNode) {
		List<String> result = new ArrayList<>();
		FlowElementsContainer flowElementsContainer = flowNode.getParentContainer();
		while (flowElementsContainer != null) {
			if (flowElementsContainer instanceof SubProcess) {
				SubProcess flowElement = (SubProcess) flowElementsContainer;
				result.add(flowElement.getId());
				flowElementsContainer = flowElement.getParentContainer();
			} else if (flowElementsContainer instanceof Process) {
				Process flowElement = (Process) flowElementsContainer;
				result.add(flowElement.getId());
				flowElementsContainer = null;
			}
		}
		// 第一层Process为第0个
		Collections.reverse(result);
		return result;
	}

	public static boolean isExecutionInsideMultiInstance(ExecutionEntity execution) {
		return getFlowElementMultiInstanceParentId(execution.getCurrentFlowElement()).isPresent();
	}

	public static Optional<String> getFlowElementMultiInstanceParentId(FlowElement flowElement) {
		FlowElementsContainer parentContainer = flowElement.getParentContainer();
		while (parentContainer instanceof Activity) {
			if (isFlowElementMultiInstance((Activity) parentContainer)) {
				return Optional.of(((Activity) parentContainer).getId());
			}
			parentContainer = ((Activity) parentContainer).getParentContainer();
		}
		return Optional.empty();
	}

	public static boolean isFlowElementMultiInstance(FlowElement flowElement) {
		if (flowElement instanceof Activity) {
			return ((Activity) flowElement).getLoopCharacteristics() != null;
		}
		return false;
	}

	/**
	 * 查询不同层级
	 *
	 * @param sourceList
	 * @param targetList
	 * @return 返回不同的层级，如果其中一个层级较深，则返回层级小的+1，从第0层开始，请注意判断是否会出现下标越界异常；返回 -1 表示在同一层
	 */
	public static Integer getDiffLevel(List<String> sourceList, List<String> targetList) {
		if (sourceList == null || sourceList.isEmpty() || targetList == null || targetList.isEmpty()) {
			throw new FlowableException("sourceList and targetList cannot be empty");
		}
		if (sourceList.size() == 1 && targetList.size() == 1) {
			// 都在第0层且不相等
			if (!sourceList.get(0).equals(targetList.get(0))) {
				return 0;
			} else {// 都在第0层且相等
				return -1;
			}
		}

		int minSize = sourceList.size() < targetList.size() ? sourceList.size() : targetList.size();
		Integer targetLevel = null;
		for (int i = 0; i < minSize; i++) {
			if (!sourceList.get(i).equals(targetList.get(i))) {
				targetLevel = i;
				break;
			}
		}
		if (targetLevel == null) {
			if (sourceList.size() == targetList.size()) {
				targetLevel = -1;
			} else {
				targetLevel = minSize;
			}
		}
		return targetLevel;
	}
}
