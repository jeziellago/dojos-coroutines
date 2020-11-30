data class StudentTask(
    val studentId: String,
    val studentName: String,
    val subject: String,
    val type: String,
    val value: Float
)

data class StudentReportCard(
    val id: String,
    val name: String,
    val subjects: Map<String, Float>,
    val status: StudentStatus,
)

enum class StudentStatus {
    APPROVED, REPROVED
}