package com.hangi.schedy;

/**
 * Created by ASUS on 2017/10/19.
 */

public class CourseFile {
    public String getSubject_str() {
        return subject_str;
    }

    public void setSubject_str(String subject_str) {
        this.subject_str = subject_str;
    }

    public String getLesson_str() {
        return lesson_str;
    }

    public void setLesson_str(String lesson_str) {
        this.lesson_str = lesson_str;
    }

    public String getTime_str() {
        return time_str;
    }

    public void setTime_str(String time_str) {
        this.time_str = time_str;
    }

    public CourseFile(String subject_str, String lesson_str, String time_str) {
        this.subject_str = subject_str;
        this.lesson_str = lesson_str;
        this.time_str = time_str;
    }

    private String subject_str;
    private String lesson_str;
    private String time_str;

}
