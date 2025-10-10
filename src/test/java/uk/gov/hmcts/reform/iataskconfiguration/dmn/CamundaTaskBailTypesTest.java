package uk.gov.hmcts.reform.iataskconfiguration.dmn;

import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.impl.DmnDecisionTableImpl;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.impl.VariableMapImpl;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.gov.hmcts.reform.iataskconfiguration.DmnDecisionTableBaseUnitTest;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.gov.hmcts.reform.iataskconfiguration.DmnDecisionTable.WA_TASK_TYPES_IA_BAIL;

class CamundaTaskBailTypesTest extends DmnDecisionTableBaseUnitTest {

    @BeforeAll
    public static void initialization() {
        CURRENT_DMN_DECISION_TABLE = WA_TASK_TYPES_IA_BAIL;
    }

    static List<Map<String, Object>> expectedTaskTypes = List.of(
        Map.of(
            "taskTypeId", "processBailApplication",
            "taskTypeName", "Process application"
        ),
        Map.of(
            "taskTypeId", "reviewInterpreterFlag",
            "taskTypeName", "Review interpreter booking"
        ),
        Map.of(
            "taskTypeId", "noticeOfChange",
            "taskTypeName", "Notice of Change"
        ),
        Map.of(
            "taskTypeId", "followUpBailSummary",
            "taskTypeName", "Follow up Home Office summary"
        ),
        Map.of(
            "taskTypeId", "reviewAdditionalEvidence",
            "taskTypeName", "Review additional evidence"
        ),
        Map.of(
            "taskTypeId", "uploadSignedDecision",
            "taskTypeName", "Upload signed decision"
        ),
        Map.of(
            "taskTypeId", "listForFurtherReview",
            "taskTypeName", "List for further review"
        ),
        Map.of(
            "taskTypeId", "postHearingRecord",
            "taskTypeName", "Post hearing – attendees, duration and recording"
        )
    );

    @Test()
    void should_evaluate_dmn_return_all_task_type_fields() {
        VariableMap inputVariables = new VariableMapImpl();
        DmnDecisionTableResult dmnDecisionTableResult = evaluateDmnTable(inputVariables);

        MatcherAssert.assertThat(dmnDecisionTableResult.getResultList(), is(expectedTaskTypes));

    }

    @Test
    void check_dmn_changed() {

        //The purpose of this test is to prevent adding new rows without being tested
        DmnDecisionTableImpl logic = (DmnDecisionTableImpl) decision.getDecisionLogic();
        assertThat(logic.getInputs().size(), is(1));
        assertThat(logic.getOutputs().size(), is(2));
        assertThat(logic.getRules().size(), is(8));
    }
}
