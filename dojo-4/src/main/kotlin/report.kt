import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

/*
    1 Recuperar arquivo tasks.json.
    2 Serializar os dados.
*/
suspend fun readStudentTasks(): List<StudentTask> {
    return withContext(Dispatchers.IO) {
        val homePath = System.getenv("HOME")
        val tasksPath = "$homePath/Documents/Github/Dojos-coroutines/dojo-4/tasks.json"
        val content = File(tasksPath).readText()
        val reportTaks: ReportTasks = Json.decodeFromString<ReportTasks>(content)
        reportTaks.tasks
    }
}

/* 3 Fazer o cálculo da nota de cada matéria de cada aluno. */
suspend fun calculateStudentsReports(studentTasks: List<StudentTask>): List<StudentReportCard> {
    return withContext(Dispatchers.Default) {

        studentTasks
                .groupBy { it.studentId }
                .map { (student, tasks) ->
                    val studentSubjects: Map<String, List<StudentTask>> = tasks.groupBy { it.subject }
                    val subjects = studentSubjects.map { (subject, values) ->
                        val subjectTask = values.first { it.type == "Task" }
                        val subjectTest = values.filter { it.type.startsWith("Test") }.maxOf { it.value }
                        val subjectMean: Float = (subjectTask.value + subjectTest) / 2f
                        subject to subjectMean
                    }

                    val subjectsCount = subjects.size
                    val finalMean = subjects.toMap().values.sum() / subjectsCount
                    val studentStatus = if (finalMean >= 7) {
                        StudentStatus.APPROVED
                    } else {
                        StudentStatus.REPROVED
                    }
                    println("Student: $student")
                    println("Subjects: $subjects")
                    println("Status: $studentStatus")
                }
        listOf()
    }
}

/*
*     4 Criar um arquivo final_media.json e salvar o resultado.
 */
fun saveStudentReportCard(report: List<StudentReportCard>) {
    TODO("Salva o report em formato json no arquivo 'students_report_card.json'")

}

fun main() {
    runBlocking {
        val studentTask = readStudentTasks()
        val studentsReports = calculateStudentsReports(studentTask)
//        saveStudentReportCard(studentTask)
    }
}