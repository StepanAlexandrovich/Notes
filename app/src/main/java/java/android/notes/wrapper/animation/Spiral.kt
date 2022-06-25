package java.android.notes.wrapper.animation

class Spiral {
    var x = 2.0
        private set
    var y = 0.0
        private set

    fun reset() {
        x = 10.0
        y = 0.0
    }

    fun process() {
        val xCopy = (x - y * 0.2) * 0.99
        val yCopy = (y + x * 0.2) * 0.99
        x = xCopy
        y = yCopy
    }
}