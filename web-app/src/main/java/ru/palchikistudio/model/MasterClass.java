package ru.palchikistudio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by Admin on 01.11.2018.
 */
public class MasterClass {
    public final static String DATE_FORMAT = "dd.MM.yyyy hh:mm";
    public final static String IMG_DIRECTORY =  "/uploads/palchiki/mk";
    public final static String DEFAULT_IMG = "default.jpg";
//    public final static String

    @JsonProperty("master_class_id")
    private final Integer masterClassId;
    @JsonProperty("name")
    private final String masterClassName;
    private String description;
    @JsonProperty("teacher_name")
    private final String teacherName;
    private final int coast;
    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private final Date masterClassDate;
    @JsonProperty("img_path")
    private final String imgPath;
    @JsonProperty("is_deleted")
    private final Boolean isDeleted;

    private MasterClass(Builder builder) {
        this.masterClassId = builder.masterClassId;
        this.masterClassName = builder.masterClassName;
        this.teacherName = builder.teacherName;
        this.description = builder.description;
        this.coast = builder.coast;
        this.masterClassDate = builder.masterClassDate;
        this.imgPath = builder.imgPath;
        this.isDeleted = builder.isDeleted;
    }

    public static class Builder {
        private Integer masterClassId = null;
        private String masterClassName;
        private String teacherName = "";
        private String description = "";
        private int coast;
        private Date masterClassDate;
        private String imgPath = null;
        private Boolean isDeleted = null;

        public Builder(String masterClassName, int coast, Date masterClassDate) {
            this.masterClassName = masterClassName;
            this.coast = coast;
            this.masterClassDate = masterClassDate;
        }

        private Builder addId(int masterClassId) {
            this.masterClassId = masterClassId;
            return this;
        }

        public Builder addMasterClassId(Integer id) {
            this.masterClassId = id;
            return this;
        }

        public Builder addTeacherName(String teacherName) {
            this.teacherName = teacherName;
            return this;
        }

        public Builder addImgPath(String imgPath) {
            this.imgPath = imgPath;
            return this;
        }

        public Builder addIsDeletedStatus(Boolean isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        public Builder addDescription(String description) {
            this.description = description;
            return this;
        }

        public MasterClass build() {
            return new MasterClass(this);
        }

        public int getMasterClassId() {
            return masterClassId;
        }

        public String getMasterClassName() {
            return masterClassName;
        }

        public String getDescription() {
            return description;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public int getCoast() {
            return coast;
        }

        public Date getMasterClassDate() {
            return masterClassDate;
        }

        public String getImgPath() {
            return imgPath;
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        @Override
        public String toString() {
            return "MasterClass{" +
                    "masterClassId=" + masterClassId +
                    ", masterClassName='" + masterClassName + '\'' +
                    ", description='" + description + '\'' +
                    ", teacherName='" + teacherName + '\'' +
                    ", coast=" + coast +
                    ", masterClassDate=" + masterClassDate +
                    ", isDeleted=" + isDeleted +
                    '}';
        }
    }

    public Integer getMasterClassId() {
        return this.masterClassId;
    }

    public String getMasterClassName() {
        return masterClassName;
    }

    public String getDescription() {
        return description;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getCoast() {
        return coast;
    }

    public Date getMasterClassDate() {
        return masterClassDate;
    }

    public String getImgPath() {
        return imgPath;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

}
