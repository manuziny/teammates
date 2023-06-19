import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DemoCourseIdGeneratorTest {
    
    @Test
    public void testGenerateDemoCourseId_EmptyInstructorEmail_ReturnsInvalidId() {
        String instructorEmail = "";
        String courseId = generateDemoCourseId(instructorEmail);
        Assertions.assertEquals("INVALID", courseId);
    }
    
    @Test
    public void testGenerateDemoCourseId_NonEmptyInstructorEmail_ReturnsValidId() {
        String instructorEmail = "instructor@example.com";
        String courseId = generateDemoCourseId(instructorEmail);
        Assertions.assertTrue(isValidCourseId(courseId));
    }
    
    @Test
    public void testGenerateDemoCourseId_CourseExists_ReturnsUniqueCourseId() {
        String instructorEmail = "instructor@example.com";
        logic.createCourse("existingCourseId");
        String courseId = generateDemoCourseId(instructorEmail);
        Assertions.assertNotEquals("existingCourseId", courseId);
        Assertions.assertTrue(isValidCourseId(courseId));
    }
    
    private String generateDemoCourseId(String instructorEmail) {
        String proposedCourseId = generateNextDemoCourseId(instructorEmail, FieldValidator.COURSE_ID_MAX_LENGTH);
        while (logic.getCourse(proposedCourseId) != null) {
            proposedCourseId = generateNextDemoCourseId(proposedCourseId, FieldValidator.COURSE_ID_MAX_LENGTH);
        }
        return proposedCourseId;
    }

}
