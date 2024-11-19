public class Images {
    private String imgId;
    private String productId;
    private String imgUrl;

    public Images(String imgId, String imgUrl) {
        this.imgId = imgId;
        this.imgUrl = imgUrl;
        this.productId = productId;
    }

    String getId() {
        return imgId;
    }

    String getUrl() {
        return imgUrl;
    }
}
