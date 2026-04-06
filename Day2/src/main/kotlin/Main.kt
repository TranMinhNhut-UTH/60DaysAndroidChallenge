// 1. DATA CLASS
data class Task(
    val id: Int,
    val name: String,
    var isDone: Boolean = false
)

// 2. SEALED CLASS (XIN HON): UI tu quyet dinh hien thi tu data nhan duoc
sealed class TaskResult {
    object Loading : TaskResult() // Dung cho cac tac vu doi load
    data class Success(val data: List<Task>) : TaskResult()
    data class Error(val message: String) : TaskResult()
}

// 3. EXTENSION FUNCTIONS
fun List<Task>.printTasks() {
    if (this.isEmpty()) {
        println("\n=> Danh sach trong.")
    } else {
        println("\n--- DANH SACH CONG VIEC ---")
        this.forEach {
            val status = if (it.id < 10) "0${it.id}" else "${it.id}"
            val mark = if (it.isDone) "[DONE]" else "[TODO]"
            println("$mark ID: $status | Ten: ${it.name}")
        }
        println("---------------------------\n")
    }
}

fun String?.toValidName(): String? = this?.trim()?.takeIf { it.isNotBlank() }

// --- LOGIC XU LY ---
class TodoManager {
    private val todoList = mutableListOf<Task>()
    private var nextId = 1

    fun addTask(name: String?): TaskResult {
        val cleanName = name.toValidName()
        return if (cleanName != null) {
            todoList.add(Task(id = nextId++, name = cleanName))
            TaskResult.Success(todoList)
        } else {
            TaskResult.Error("Ten khong duoc de trong!")
        }
    }

    fun deleteTask(id: Int): TaskResult {
        val removed = todoList.removeIf { it.id == id }
        return if (removed) TaskResult.Success(todoList)
        else TaskResult.Error("Khong tim thay ID: $id de xoa")
    }

    // Nang cap: Khong hardcode true, cho phep truyen vao trang thai muon sua
    fun updateTask(id: Int, status: Boolean): TaskResult {
        val task = todoList.find { it.id == id }
        return if (task != null) {
            task.isDone = status
            TaskResult.Success(todoList)
        } else {
            TaskResult.Error("Khong tim thay ID: $id de cap nhat")
        }
    }
}

// --- HAM MAIN ---
fun main() {
    val manager = TodoManager()

    while (true) {
        println("1. Them | 2. Xoa | 3. Cap nhat | 4. Thoat")
        print("Chon: ")
        val choice = readlnOrNull() ?: ""

        val result: TaskResult = when (choice) {
            "1" -> {
                print("Nhap ten task: ")
                manager.addTask(readlnOrNull())
            }
            "2" -> {
                print("Nhap ID can xoa: ")
                val id = readlnOrNull()?.toIntOrNull() ?: -1
                manager.deleteTask(id)
            }
            "3" -> {
                print("Nhap ID can sua: ")
                val id = readlnOrNull()?.toIntOrNull() ?: -1
                print("Nhap trang thai (1: DONE, 0: TODO): ")
                val statusInput = readlnOrNull()
                val status = statusInput == "1"
                manager.updateTask(id, status)
            }
            "4" -> break
            else -> TaskResult.Error("Lua chon khong hop le!")
        }

        // UI tu quyet dinh message dua tren kieu du lieu tra ve
        when (result) {
            is TaskResult.Loading -> println("... Dang xu ly ...")
            is TaskResult.Success -> {
                println(">>> Thuc hien thao tac THANH CONG!")
                result.data.printTasks()
            }
            is TaskResult.Error -> {
                println("!!! LOI: ${result.message}")
            }
        }
    }
    println("Tam biet!")
}