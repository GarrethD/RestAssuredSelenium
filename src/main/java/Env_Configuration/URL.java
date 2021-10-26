package Env_Configuration;

public enum URL {
    BASE_URL("https://demoqa.com/");


    private String url;

    URL(String url) {
        this.url = url;
    }
    public String getURL() {
        return this.url;

    }
}
