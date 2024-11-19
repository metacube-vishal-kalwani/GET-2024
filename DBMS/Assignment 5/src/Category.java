public class Category {

    private String categoryName;
    private int childCategoryCount;

    Category(String name, int count) {

        this.categoryName = name;
        this.childCategoryCount = count;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getChildCategoryCount() {
        return childCategoryCount;
    }

}
