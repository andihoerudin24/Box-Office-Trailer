package com.tutorkomputer.youtube.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Video {

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    @SerializedName("results")
    private List<Results> results;




    public class Results {

        @SerializedName("key")
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @SerializedName("name")
        private String name;


    }

}
