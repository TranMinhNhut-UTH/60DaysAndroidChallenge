package day01

// 1. Định nghĩa "Khung xương" cho công việc
data class Task(
    val id: Int,
    val name: String,
    var isDone: Boolean = false
)

// Danh sách lưu trữ dùng chung cho các hàm`
val todoList = arrayListOf<Task>()
var nextId = 1 // Tự động tăng ID để không bị trùng

fun main() {
    println("======================================")
    println("   UPGRADED TODO APP: DATA CLASS EDI   ")
    println("======================================")

    while (true) {
        println("\n--- MENU ---")
        println("1. Add task")
        println("2. Show tasks")
        println("3. Mark done")
        println("4. Delete task")
        println("0. Exit")
        print("Selection: ")

        val choice = readlnOrNull() ?: "0"

        when (choice) {
            "1" -> {
                print("Enter task name: ")
                val name = readlnOrNull()
                addTask(name)
            }
            "2" -> showTasks()
            "3" -> {
                print("Enter ID to mark done: ")
                val idInput = readlnOrNull()?.toIntOrNull() // Chống crash nếu nhập chữ
                if (idInput != null) markDone(idInput) else println("(!) ID must be a number")
            }
            "4" -> {
                print("Enter ID to delete: ")
                val idInput = readlnOrNull()?.toIntOrNull()
                if (idInput != null) deleteTask(idInput) else println("(!) ID must be a number")
            }
            "0" -> {
                println("Goodbye! See you in Day 02.")
                break
            }
            else -> println("Invalid selection!")
        }
    }
}

// --- HỆ THỐNG HÀM XỬ LÝ (LOGIC TÁCH BIỆT) ---

fun addTask(name: String?) {
    // Xử lý Null và Blank cực gọn
    if (name.isNullOrBlank()) {
        println("(!) Cannot add empty task.")
        return
    }

    val newTask = Task(id = nextId++, name = name)
    todoList.add(newTask)
    println("=> Added: [${newTask.id}] ${newTask.name}")
}

fun showTasks() {
    if (todoList.isEmpty()) {
        println("(List is empty)")
        return
    }
    println("\nYOUR TASKS:")
    todoList.forEach { task ->
        val status = if (task.isDone) "x" else " "
        println("${task.id}. [$status] ${task.name}")
    }
}

fun markDone(id: Int) {
    // Tìm task có ID khớp, nếu không thấy trả về null
    val task = todoList.find { it.id == id }

    if (task != null) {
        task.isDone = true
        println("=> Task [${id}] is now DONE!")
    } else {
        println("(!) Task ID $id not found.")
    }
}

fun deleteTask(id: Int) {
    // removeIf trả về true nếu xóa thành công
    val removed = todoList.removeIf { it.id == id }

    if (removed) {
        println("=> Deleted task [$id]")
    } else {
        println("(!) Could not find task [$id] to delete.")
    }
}