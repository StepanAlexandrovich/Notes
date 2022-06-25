package java.android.notes

object Helper {
    fun delay(delay: Int) {
        try {
            Thread.sleep(delay.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}