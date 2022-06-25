package java.android.notes.core

class Note {
    // set //
    // get //
    public var date: String = ""
    public var headline = ""
    public var description = ""
    public var body = ""
    public var id: String? = null

    public fun setterHeadLine(text:String){ headline = text }
    public fun setterDescription(text:String){ description = text }
    public fun setterBody(text:String){ body = text }
    public fun setterDate(text:String){ date = text }

    constructor() {}
    constructor(date: String) {
        this.date = date
    }

    // logic //
    fun ready(): Boolean {
        return if (headline == "") {
            false
        } else true
    }

}