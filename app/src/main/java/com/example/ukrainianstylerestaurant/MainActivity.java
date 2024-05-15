package com.example.ukrainianstylerestaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ukrainianstylerestaurant.adapter.CategoryAdapter;
import com.example.ukrainianstylerestaurant.adapter.CourseAdapter;
import com.example.ukrainianstylerestaurant.model.Category;
import com.example.ukrainianstylerestaurant.model.Course;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    static CourseAdapter courseAdapter;
    static List<Course> courseList = new ArrayList<>();
    static List<Course> fullCoursesList = new ArrayList<>();

    private boolean isCategoryAdapterSet = false;
    private boolean isCourseAdapterSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1,"Борщ"));
        categoryList.add(new Category(2,"Вареники"));
        categoryList.add(new Category(3,"Банош"));
        categoryList.add(new Category(4,"Галушки"));
        categoryList.add(new Category(5,"Напої"));

        setCategoryRecycler(categoryList);
        if(courseList.isEmpty()) {
            courseList.add(new Course(1, "borsch", "Борщ звичайний", "100", "0/5", "#424345", 1));
            courseList.add(new Course(2, "spicy_borsch", "Борщ пікантний", "120", "2/5", "#424345", 1));
            courseList.add(new Course(3, "cheese_vareniks", "Вареники з\nсиром", "130", "0/5", "#424345", 2));
            courseList.add(new Course(4, "potato_vareniks", "Вареники з\nкартоплею", "110", "1/5", "#424345", 2));
            courseList.add(new Course(5, "banosh_brinza", "Банош з бринзою", "160", "0/5", "#424345", 3));
            courseList.add(new Course(6, "banosh_spicy_mushroom", "Банош з соусом\nз грибами\nта перцем чілі", "170", "4/5", "#424345", 3));
            courseList.add(new Course(7, "galushki_meat", "Галушки з м'ясом", "150", "0/5", "#424345", 4));
            courseList.add(new Course(8, "galushki_cabbage", "Галушки з\nквашеною\nкапустою", "100", "1/5", "#424345", 4));
            courseList.add(new Course(9, "kvas", "Квас", "70", "0/5", "#424345", 5));
            courseList.add(new Course(10, "uzvar", "Узвар з\nсухофруктів", "120", "0/5", "#424345", 5));

            fullCoursesList.addAll(courseList);
        }
        setCourseRecycler(courseList);
    }


    public void aboutUs(View view){
        Intent intent = new Intent(this, AboutUs.class);
        startActivity(intent);
    }

    public void goToContacts(View view){
        Intent intent = new Intent(this, Contacts.class);
        startActivity(intent);
    }
    public void openShoppingCart(View view){
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }

    private void setCourseRecycler(List<Course> courseList) {
        if(!isCourseAdapterSet) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

            courseRecycler = findViewById(R.id.courseRecycler);
            courseRecycler.setLayoutManager(layoutManager);

            courseAdapter = new CourseAdapter(this, courseList);
            courseRecycler.setAdapter(courseAdapter);

            isCourseAdapterSet = true;
        }
    }

    private void setCategoryRecycler(List<Category> categoryList) {
        if(!isCategoryAdapterSet) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

            categoryRecycler = findViewById(R.id.categoryRecycler);
            categoryRecycler.setLayoutManager(layoutManager);

            categoryAdapter = new CategoryAdapter(this, categoryList);
            categoryRecycler.setAdapter(categoryAdapter);

            isCategoryAdapterSet = true;
        }
    }

    public void returnAllCourses(View view){
        courseList.clear();
        courseList.addAll(fullCoursesList);
        courseAdapter.notifyDataSetChanged();
    }

    public static void showCoursesByCategory(int category){

        courseList.clear();
        courseList.addAll(fullCoursesList);

        List<Course> filterCourses = new ArrayList<>();
        for(Course c : courseList){
            if(c.getCategory() == category){
                filterCourses.add(c);
            }
        }
        courseList.clear();
        courseList.addAll(filterCourses);

        courseAdapter.notifyDataSetChanged();
    }
}