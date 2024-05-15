package com.example.ukrainianstylerestaurant.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ukrainianstylerestaurant.CoursePage;
import com.example.ukrainianstylerestaurant.R;
import com.example.ukrainianstylerestaurant.model.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    Context context;
    List<Course> courses;

    public CourseAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseItems = LayoutInflater.from(context).inflate(R.layout.course_item, parent, false);
        return new CourseAdapter.CourseViewHolder(courseItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.courseBg.setCardBackgroundColor(Color.parseColor(courses.get(holder.getAdapterPosition()).getColor()));

        int imageId = context.getResources().getIdentifier(courses.get(holder.getAdapterPosition()).getImg(),"drawable",context.getPackageName());
        holder.courseImage.setImageResource(imageId);

        holder.courseTitle.setText(courses.get(holder.getAdapterPosition()).getTitle());
        holder.coursePrice.setText(courses.get(holder.getAdapterPosition()).getPrice());
        holder.coursePepper.setText(courses.get(holder.getAdapterPosition()).getPepper());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, CoursePage.class);

            intent.putExtra("courseBg", Color.parseColor(courses.get(holder.getAdapterPosition()).getColor()));
            intent.putExtra("courseImage", imageId);
            intent.putExtra("courseTitle", courses.get(holder.getAdapterPosition()).getTitle());
            intent.putExtra("coursePrice", courses.get(holder.getAdapterPosition()).getPrice());
            intent.putExtra("coursePepper", courses.get(holder.getAdapterPosition()).getPepper());
            intent.putExtra("courseId", courses.get(holder.getAdapterPosition()).getId());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static final class CourseViewHolder extends RecyclerView.ViewHolder{

        CardView courseBg;
        ImageView courseImage;
        TextView courseTitle, coursePrice, coursePepper;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            courseBg = itemView.findViewById(R.id.courseBg);
            courseImage = itemView.findViewById(R.id.courseImage);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            coursePrice = itemView.findViewById(R.id.coursePrice);
            coursePepper = itemView.findViewById(R.id.coursePepper);
        }
    }

}
