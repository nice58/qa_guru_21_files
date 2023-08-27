package guru.qa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class ProductModel {

    @JsonProperty("name")
    private String name;
    @JsonProperty("allow_to_sell")
    private boolean allowToSell;
    @JsonProperty("cost_price")
    private String costPrice;
    private SubjectModel subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAllowToSell() {
        return allowToSell;
    }

    public void setAllowToSell(boolean allowToSell) {
        this.allowToSell = allowToSell;
    }

    public String getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(String costPrice) {
        this.costPrice = costPrice;
    }

    public SubjectModel getSubject() {
        return subject;
    }

    public void setSubject(SubjectModel subject) {
        this.subject = subject;
    }

    public static class SubjectModel {
        @JsonProperty("name_subject")
        private String nameSubject;
        @JsonProperty("description_subject")
        private String descriptionSubject;

        public String getNameSubject() {
            return nameSubject;
        }

        public void setNameSubject(String nameSubject) {
            this.nameSubject = nameSubject;
        }

        public String getDescriptionSubject() {
            return descriptionSubject;
        }

        public void setDescriptionSubject(String descriptionSubject) {
            this.descriptionSubject = descriptionSubject;
        }
    }

}
