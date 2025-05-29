package com.davidwerfelmann.course_scheduler.utilities;

import com.davidwerfelmann.course_scheduler.data.CourseRepository;
import com.davidwerfelmann.course_scheduler.models.Course;
import com.davidwerfelmann.course_scheduler.models.Rotation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class WebScraper {

    @Autowired
    private CourseRepository courseRepository;

    public void importCatalog(String url) {
        System.out.println("Begin catalog import.\n");

        try {
            Document doc = Jsoup.connect(url).get();

            Elements h3Eelements = doc.select("h3");

            for (Element h3 : h3Eelements) {
                if (h3.text().startsWith("MUSC")) {
                    String courseInfo = h3.text();
                    Element next = h3.nextElementSibling();
                    String courseDescription = "";

                    if (next != null && next.tagName().equals("p")) {
                        courseDescription = next.text();
                    }

                    Pattern pattern = Pattern.compile("\\((\\d+)(?:-(\\d+))?\\)$");
                    Matcher matcher = pattern.matcher(courseInfo);

                    int minCreditHours = 0;
                    int maxCreditHours = 0;

                    if (matcher.find()) {
                        minCreditHours = Integer.parseInt(matcher.group(1));
                        maxCreditHours = matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : minCreditHours;
                        courseInfo = courseInfo.replaceAll("\\s*\\((\\d+)(?:-(\\d+))?\\)$", "").trim();
                    }

                    String coursePrefix = courseInfo.substring(0, 4);
                    String courseNumber = courseInfo.substring(5, 9);
                    String courseTitle = courseInfo.substring(10);
                    Set<Rotation> emptyRotationSet = new HashSet<>();

                    Course courseToImport = new Course(courseNumber, courseTitle, minCreditHours, maxCreditHours, emptyRotationSet, courseDescription, null);

                    System.out.println("Course Number: " + coursePrefix + " " + courseNumber);
                    System.out.println("Course Title: " + courseTitle);
                    System.out.println("Min Credits: " + minCreditHours);
                    System.out.println("Max Credits: " + maxCreditHours);
                    System.out.println("Course Description: " + courseDescription + "\n");

                    List<Course> existingCourses = courseRepository.findAll();

                    boolean courseExists = existingCourses.stream()
                            .anyMatch(course -> course.getCourseNumber().equals(courseToImport.getCourseNumber()));

                    if (!courseExists) {
                        courseRepository.save(courseToImport);
                    }
                }
            }

            System.out.println("Catalog import complete.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
