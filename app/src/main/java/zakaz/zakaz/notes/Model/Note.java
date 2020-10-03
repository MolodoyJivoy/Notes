package zakaz.zakaz.notes.Model;

public class Note {
    private String uniqueID;

    private String m_Zagolovok;
    private String m_Today;
    private String m_Thanks;
    private String m_Task;
    private String m_Sleep;
    private String m_Mood;
    private String[] m_Tag;
    private String m_Date;
    private String m_Lucky;
    private String m_Unlucky;

    private StatusNote m_NoteStatus;

    public Note() {
    }

    public Note(String m_Zagolovok, String m_Today, String m_Thanks, String m_Task, String m_Sleep, String m_Mood, String m_Date, String[] m_Tag, StatusNote m_NoteStatus, String uniqueID, String m_Lucky, String m_Unlucky) {
        this.m_Zagolovok = m_Zagolovok;
        this.m_Today = m_Today;
        this.m_Thanks = m_Thanks;
        this.m_Task = m_Task;
        this.m_Sleep = m_Sleep;
        this.m_Mood = m_Mood;
        this.m_Tag = m_Tag;
        this.m_Date = m_Date;
        this.m_NoteStatus = m_NoteStatus;
        this.uniqueID = uniqueID;
        this.m_Lucky = m_Lucky;
        this.m_Unlucky = m_Unlucky;
    }



    public String[] getTag() {
        return m_Tag;
    }

    public void setTag(String[] m_Tag) {
        this.m_Tag = m_Tag;
    }

    public String getZagolovok() {
        return m_Zagolovok;
    }

    public void setZagolovok(String m_Zagolovok) {
        this.m_Zagolovok = m_Zagolovok;
    }

    public String getToday() {
        return m_Today;
    }

    public void setToday(String m_Today) {
        this.m_Today = m_Today;
    }

    public String getThanks() {
        return m_Thanks;
    }

    public void setThanks(String m_Thanks) {
        this.m_Thanks = m_Thanks;
    }

    public String getTask() {
        return m_Task;
    }

    public void setTask(String m_Task) {
        this.m_Task = m_Task;
    }

    public String getSleep() {
        return m_Sleep;
    }

    public void setSleep(String m_Sleep) {
        this.m_Sleep = m_Sleep;
    }

    public String getMood() {
        return m_Mood;
    }

    public void setMood(String m_Mood) {
        this.m_Mood = m_Mood;
    }


    public String getDate() {
        return m_Date;
    }

    public void setDate(String m_Date) {
        this.m_Date = m_Date;
    }

    public StatusNote getNoteStatus() {
        return m_NoteStatus;
    }

    public void setNoteStatus(StatusNote m_NoteStatus) {
        this.m_NoteStatus = m_NoteStatus;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getLucky() {
        return m_Lucky;
    }

    public void setLucky(String m_Lucky) {
        this.m_Lucky = m_Lucky;
    }

    public String getUnlucky() {
        return m_Unlucky;
    }

    public void setUnlucky(String m_Unlucky) {
        this.m_Unlucky = m_Unlucky;
    }
}
