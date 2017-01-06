package teammates.test.cases.automated;

import org.testng.annotations.Test;

import teammates.common.datatransfer.DataBundle;
import teammates.common.datatransfer.FeedbackSessionAttributes;
import teammates.common.util.Const;
import teammates.common.util.Const.ParamsNames;
import teammates.ui.automated.FeedbackSessionUnpublishedEmailWorkerAction;

/**
 * SUT: {@link FeedbackSessionUnpublishedEmailWorkerAction}.
 */
public class FeedbackSessionUnpublishedEmailWorkerActionTest extends BaseAutomatedActionTest {
    
    private static final DataBundle dataBundle = getTypicalDataBundle();
    
    @Override
    protected String getActionUri() {
        return Const.TaskQueue.FEEDBACK_SESSION_UNPUBLISHED_EMAIL_WORKER_URL;
    }
    
    @Test
    public void allTests() {
        FeedbackSessionAttributes session1 = dataBundle.feedbackSessions.get("session1InCourse1");
        
        String[] submissionParams = new String[] {
                ParamsNames.EMAIL_COURSE, session1.getCourseId(),
                ParamsNames.EMAIL_FEEDBACK, session1.getFeedbackSessionName()
        };
        
        FeedbackSessionUnpublishedEmailWorkerAction action = getAction(submissionParams);
        action.execute();
        
        // 5 students and 5 instructors in course1
        verifySpecifiedTasksAdded(action, Const.TaskQueue.SEND_EMAIL_QUEUE_NAME, 10);
    }
    
    @Override
    protected FeedbackSessionUnpublishedEmailWorkerAction getAction(String... submissionParams) {
        return (FeedbackSessionUnpublishedEmailWorkerAction)
                gaeSimulation.getAutomatedActionObject(getActionUri(), submissionParams);
    }
    
}
