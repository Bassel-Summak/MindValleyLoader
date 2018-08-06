package com.mindvalleytestapp.util;

import com.mindvalleytestapp.mvp.model.extra.Category;
import java.util.List;

public class ToolsFunctions {


    public String getCategoriesString(List<Category> categoryList){
        String categories = "";
        for (Category category : categoryList) {
            categories = categories + category.getTitle() +" ";
        }
        return categories;
    }

    public String getDate_YearMonthDay(String fullDate) {
        return fullDate.substring(0, Math.min(fullDate.length(), 10));
    }

}
