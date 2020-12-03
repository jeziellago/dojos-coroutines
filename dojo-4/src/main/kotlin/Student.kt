
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudentTask(
        @SerialName("id")
        val studentId: String,
        @SerialName("name")
        val studentName: String,
        @SerialName("subject")
        val subject: String,
        @SerialName("type")
        val type: String,
        @SerialName("value")
        val value: Float
)

@Serializable
data class StudentReportCard(
        @SerialName("id")
        val id: String,
        @SerialName("name")
        val name: String,
        @SerialName("subjects")
        val subjects: Map<SubjectName, SubjectResult>,
        @SerialName("status")
        val status: StudentStatus,
        @SerialName("final_value")
        val final: Float
)

@Serializable
data class ReportTasks(
        @SerialName("tasks")
        val tasks: List<StudentTask>
)

@Serializable
enum class StudentStatus {
    APPROVED,
    REPROVED
}

typealias SubjectName = String
typealias SubjectResult = Float