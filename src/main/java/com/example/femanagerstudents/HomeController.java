package com.example.femanagerstudents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class    HomeController {
    public AnchorPane homeview;
    public AnchorPane studentview;
    public AnchorPane subjectview;
    public TableView<Student> tableview;

    private final ObservableList<Student> students = FXCollections.observableArrayList();
    private final ObservableList<Subject> subjects = FXCollections.observableArrayList();
    public TextField nametextfield;
    public TextField studenttextfield;
    public DatePicker datepicker;
    public ComboBox<String> Gendercombobox;
    public Button deletebutton;
    public Button updatebutton;
    public ComboBox<String> coursecombobox;
    public Button addsubjectbutton;

    public Button deletesubjectbutton;
    public TextField subjectIDtextfield;
    public TextField subjectnametextfield;
    public TableView<Subject> tablesubjectview;
    public Button updatesubjectbutton;
    public TextField GPAtextfield;
    public TextField searchtextfield;
    public Button searchbutton;
    public Button repeatbutton;
    public Button resetbutton;

    public void initialize(){
        Gendercombobox.getItems().addAll(
                "Nam",
                "Nữ"
        );
        //set lựa chọn cho combobox
        tableview.setItems(students);
        tablesubjectview.setItems(subjects);

        loadStudentsFromFile("data.txt");
        loadSubjectstoFile("dataSubject.txt");

        resetbutton.setOnAction(this::reset);

        updatebutton.setOnAction(this::update);
        // Thiết lập sự kiện cho nút cập nhật
        coursecombobox.getItems().addAll(
                "CNTT",
                "Economy",
                "Law"
        );

    }


    public void home(ActionEvent actionEvent) {
        homeview.setVisible(true);
        studentview.setVisible(false);
        subjectview.setVisible(false);
        //hiện homeview
    }

    public void student(ActionEvent actionEvent) {
        homeview.setVisible(false);
        studentview.setVisible(true);
        subjectview.setVisible(false);
        //hiện studentview
    }

    public void subject(ActionEvent actionEvent) {
        homeview.setVisible(false);
        studentview.setVisible(false);
        subjectview.setVisible(true);
        //hiện subjectview
    }

    public void gender(ActionEvent actionEvent) {
    }

    public void studenttext(ActionEvent actionEvent) {
    }

    public void nametext(ActionEvent actionEvent) {
    }

    public void datetext(ActionEvent actionEvent) {
    }

    public void add(ActionEvent actionEvent) {
        String nametext = nametextfield.getText();
        String studentext = studenttextfield.getText();
        LocalDate localdate = datepicker.getValue();
        String gender = Gendercombobox.getValue();
        Float GPA = Float.valueOf(GPAtextfield.getText());
        students.add(new Student(Integer.parseInt(studentext),nametext,gender,localdate,GPA));
        //xét kiểu dữ liệu cho từng fx id

        saveStudentsToFile("data.txt");
    }

    public void update(ActionEvent actionEvent) {
        Student selectedStudent = tableview.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            String nametext = nametextfield.getText();
            String studentext = studenttextfield.getText();
            LocalDate localdate = datepicker.getValue();
            String gender = Gendercombobox.getValue();

            // Cập nhật thông tin của sinh viên được chọn
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                if (student.getID() == selectedStudent.getID()) {
                    student.setName(nametext);
                    student.setGender(gender);
                    student.setDate(localdate);
                    students.set(i, student); // Sửa lại sinh viên tại chỉ mục i
                    break; // Thoát khỏi vòng lặp sau khi sửa
                }
            }
           // Ghi lại thông tin vào tệp văn bản
            saveStudentsToFile("data.txt");
        } else {
            // Hiển thị thông báo cho người dùng nếu không có sinh viên nào được chọn
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn một sinh viên để cập nhật.");
            alert.showAndWait();
        }
    }

    public void delete(ActionEvent actionEvent) {
        students.remove(tableview.getSelectionModel().getSelectedItem());

        saveStudentsToFile("data.txt");
    }
    //xóa student

    public void saveStudentsToFile(String filePath ) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            for (Student student : students) {
                String line = String.format("%d,%s,%s,%s,%.2f",
                        student.getID(),
                        student.getName(),
                        student.getGender(),
                        student.getDate(),
                        student.getGPA());
                writer.println(line);
            }
            System.out.println("Danh sách sinh viên đã được lưu vào tệp " + filePath);
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu danh sách sinh viên vào tệp " + filePath + ": " + e.getMessage());
        }
    }
    //tải dữ liệu student vào data.txt
    public void loadStudentsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) { // Kiểm tra xem có đủ phần tử để tạo sinh viên không, bao gồm GPA
                    int studentId = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String gender = parts[2];
                    LocalDate birthdate = LocalDate.parse(parts[3], DateTimeFormatter.ISO_LOCAL_DATE);
                    float GPA = Float.parseFloat(parts[4]); // Đọc và chuyển đổi GPA từ chuỗi sang float
                    students.add(new Student(studentId, name, gender, birthdate, GPA)); // Thêm sinh viên mới vào danh sách
                }
            }
            System.out.println("Danh sách sinh viên đã được tải từ tệp " + filePath);
        } catch (IOException e) {
            System.err.println("Lỗi khi tải danh sách sinh viên từ tệp " + filePath + ": " + e.getMessage());
        }
        //lưu student trong data.txt

    }

    public void subjectIDtext(ActionEvent actionEvent) {
    }

    public void subjectnametext(ActionEvent actionEvent) {
    }

    public void course(ActionEvent actionEvent) {
    }

    public void addsubject(ActionEvent actionEvent) {
        String subjectIDtext = subjectIDtextfield.getText();
        String namesubjecttext = subjectnametextfield.getText();
        String course = coursecombobox.getValue();
        subjects.add(new Subject(Integer.parseInt(subjectIDtext),namesubjecttext,course));
        saveSubjectstoFile("dataSubject.txt");
    }


    public void deletesubject(ActionEvent actionEvent) {
        subjects.remove(tablesubjectview.getSelectionModel().getSelectedItem());

        saveSubjectstoFile("dataSubject.txt");
    //xóa student
    }


    public void saveSubjectstoFile(String filePath ) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            for (Subject subject : subjects) {
                String line = String.format("%s,%s,%s",
                        subject.getSubject_Name(),
                        subject.getSubject_ID(),
                        subject.getCourse());
                writer.println(line);
            }
            System.out.println("Danh sách môn học đã được lưu vào tệp " + filePath);
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu danh sách môn học vào tệp " + filePath + ": " + e.getMessage());
        }
        //lưu vào File
    }

    public void updatesubject(ActionEvent actionEvent) {
        Subject selectedSubject = tablesubjectview.getSelectionModel().getSelectedItem();
        if (selectedSubject != null) {
            String subjectnametext = subjectnametextfield.getText();
            String subjectIDtext = subjectIDtextfield.getText();
            String course = coursecombobox.getValue();

            // Cập nhật thông tin của môn được chọn
            for (int i = 0; i < subjects.size(); i++) {
                Subject subject = subjects.get(i);
                if (subject.getSubject_ID() == selectedSubject.getSubject_ID()) {
                    subject.setSubject_Name(subjectnametext);
                    subject.setSubject_ID(Integer.parseInt(subjectIDtext));
                    subject.setCourse(course);
                    subjects.set(i, subject); // Sửa lại mục tại chỉ mục i
                    break; // Thoát khỏi vòng lặp sau khi sửa
                }
            }
            // Ghi lại thông tin vào tệp văn bản
            saveSubjectstoFile("dataSubject.txt");
        } else {
            // Hiển thị thông báo cho người dùng nếu không có môn nào được chọn
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn một môn để cập nhật.");
            alert.showAndWait();
        }
    }
    public void loadSubjectstoFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) { // Kiểm tra xem có đủ phần tử để tạo môn học không
                    String subjectName = parts[0];
                    int subjectId = Integer.parseInt(parts[1]);
                    String course = parts[2];
                    subjects.add(new Subject(subjectId, subjectName, course)); // Thêm môn học mới vào danh sách
                }
            }
            System.out.println("Danh sách môn học đã được tải từ tệp " + filePath);
        } catch (IOException e) {
            System.err.println("Lỗi khi tải danh sách môn học từ tệp " + filePath + ": " + e.getMessage());
        }
    }

    public void GPAtext(ActionEvent actionEvent) {
    }

    public void search(ActionEvent actionEvent) {
        String searchText = searchtextfield.getText(); // Lấy ID nhập vào từ trường tìm kiếm

        ObservableList<Student> searchResults = FXCollections.observableArrayList(); // Danh sách kết quả tìm kiếm

        for (Student student : students) {
            if (Integer.toString(student.getID()).equals(searchText)) { // Kiểm tra xem ID của sinh viên có khớp với ID tìm kiếm không
                searchResults.add(student); // Nếu có, thêm sinh viên vào danh sách kết quả
            }
        }

        tableview.setItems(searchResults); // Hiển thị danh sách kết quả trên TableView
    }

    public void repeat(ActionEvent actionEvent) {
        searchtextfield.clear(); // Xóa nội dung trong trường tìm kiếm
        tableview.setItems(students); // Hiển thị lại danh sách sinh viên ban đầu trên TableView
    }
    public ObservableList<Student> getStudents() {
        return students;
    }

    public void reset(ActionEvent actionEvent) {
        studenttextfield.clear();
        nametextfield.clear();
        datepicker.setValue(null);
        Gendercombobox.setValue(null);
        GPAtextfield.clear();
        tableview.getSelectionModel().clearSelection();
        }
}
