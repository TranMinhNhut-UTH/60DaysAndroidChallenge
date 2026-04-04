package day01

fun main() {
    // val: Danh sách là cố định (không thay thế bằng danh sách khác)
    // ArrayList: Nội dung bên trong có thể thêm/bớt (Mutable)
    val todoList = arrayListOf<String>()

    println("================================")
    println("   WELCOME TO DAY 1: TODO CLI   ")
    println("================================")

    while (true) {
        println("\n--- MENU ---")
        println("1. Them cong viec")
        println("2. Xem danh sach")
        println("3. Thoat")
        print("Lua chon cua ban (1-3): ")

        // readln() có thể trả về chuỗi rỗng, dùng ?. và ?: để an toàn
        val choice = readlnOrNull() ?: "" //Chống EOF, chương trình vẫn chạy khi nhấn ctrl+D ctrl+Z vì khi đó choice nhận về giá trị null. Còn khi dùng readln() thì nó không thấy giá trị và crash.

        when (choice) {
            "1" -> {
                print("Nhap ten cong viec: ")
                val input = readlnOrNull() //readlnOrNull trả về giá trị nullable nên input cũng là biến nullable (Tương đương val input: String? = readlnOrNull() nhưng nếu dùng cách 2 thì không dùng hàm lenght() được)

                // KỸ THUẬT NULL SAFETY: 
                // Nếu input null hoặc chỉ có khoảng trắng, dùng "Việc mặc định"
                val taskName = input?.takeIf { it.isNotBlank() } ?: "Viec khong ten"
                // thay vì input?.takeIf({ inputStr -> inputStr.isNotBlank() }) thì dùng lambda it; it ở đây không cần đặt tên vì hàm takeIf chỉ làm việc với 1 biến input

                todoList.add(taskName)
                println("=> Da them: \"$taskName\"")
            }
            "2" -> {
                println("\nDANH SACH CONG VIEC:")
                if (todoList.isEmpty()) {
                    println("(Trong rong)")
                } else {
                    // Dùng Lambda và 'it' để in danh sách
                    todoList.forEachIndexed { index, task ->
                        println("${index + 1}. $task")
                    }
                }
            }
            "3" -> {
                println("Tam biet! Hen gap lai o day02")
                break
            }
            else -> println("Lua chon khong hop le, vui long nhap lai!")
        }
    }
}