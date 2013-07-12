package com.spoobrain.studentgrades;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBTools extends SQLiteOpenHelper {

	public DBTools(Context applicationContext) {
		
		super(applicationContext, "studentGrades.db", null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String createClassTable = "CREATE TABLE classes (" +
								  "classId INTEGER PRIMARY KEY, " +
								  "className TEXT, " +
								  "classType TEXT, " +
								  "classNumber INTEGER, " +
								  "classSection TEXT, " +
								  "classYear INTEGER)";
		
		db.execSQL(createClassTable);
		
		String createStudentTable = "CREATE TABLE students (" +
								    "studentId INTEGER PRIMARY KEY, " +
								    "studentName TEXT, " +
								    "classId INTEGER, " +
								    "FOREIGN KEY (classId) " +
								    "REFERENCES classes(classId) " +
								    "ON DELETE CASCADE)";
		
		db.execSQL(createStudentTable);
		
		String createStudentInfoTable = "CREATE TABLE student_info (" +
										"studentId INTEGER PRIMARY KEY, " +
										"studentDOB DATE, " +
										"studentGPA REAL, " +
										"FOREIGN KEY (studentId) " +
										"REFERENCES students(studentId) " +
										"ON DELETE CASCADE)";
		
		db.execSQL(createStudentInfoTable);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		String query = "DROP TABLE IF EXISTS classes";
		
		db.execSQL(query);
		
		query = "DROP TABLE IF EXISTS students";
		
		db.execSQL(query);
		
		query = "DROP TABLE IF EXISTS student_info";
		
		db.execSQL(query);
		
		onCreate(db);
		
	}
	
	public void insertClass(HashMap<String, String> queryValues) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put("className", queryValues.get("className"));
		values.put("classType", queryValues.get("classType"));
		values.put("classNumber", queryValues.get("classNumber"));
		values.put("classSection", queryValues.get("classSection"));
		values.put("classYear", queryValues.get("classYear"));
		
		db.insert("classes", null, values);
		db.close();
		
	}
	
	public void insertStudent(HashMap<String, String> queryValues) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("studentName", queryValues.get("studentName"));
		values.put("classId", queryValues.get("classId"));

		db.insert("students", null, values);
		db.close();

	}
	
	public void insertStudentInfo(HashMap<String, String> queryValues) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("studentDOB", queryValues.get("studentDOB"));
		values.put("studentGPA", queryValues.get("studentGPA"));
		
		db.insert("student_info", null, values);
		db.close();

	}
	
	public int updateClass(HashMap<String, String> queryValues) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("className", queryValues.get("className"));
		values.put("classType", queryValues.get("classType"));
		values.put("classNumber", queryValues.get("classNumber"));
		values.put("classSection", queryValues.get("classSection"));
		values.put("classYear", queryValues.get("classYear"));

		return db.update("classes", values, "classId " + " = ?", new String[] {queryValues.get("classId")});

	}
	
	public int updateStudent(HashMap<String, String> queryValues) {
		
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("studentName", queryValues.get("studentName"));
		values.put("classId", queryValues.get("classId"));
		
		return db.update("students", values, "studentId " + " = ?", new String[] {queryValues.get("studentId")});
		
	}
	
	public int updateStudentInfo(HashMap<String, String> queryValues) {
		
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("studentDOB", queryValues.get("studentDOB"));
		values.put("studentGPA", queryValues.get("studentGPA"));
		
		return db.update("student_info", values, "studentId " + " = ?", new String[] {queryValues.get("studentId")});
	
	}
	
	public void deleteClass(String id) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		String query = "DELETE FROM classes " +
					   "WHERE classId = '" + id + "'";
		
		db.execSQL(query);
		
		db.close();			   
	
	}
	
	public void deleteStudent(String id) {

		SQLiteDatabase db = this.getWritableDatabase();

		String query = "DELETE FROM students " +
				"WHERE studentId = '" + id + "'";

		db.execSQL(query);

		db.close();			   

	}
	
	public void deleteStudentInfo(String id) {

		SQLiteDatabase db = this.getWritableDatabase();

		String query = "DELETE FROM student_info " +
				"WHERE studentId = '" + id + "'";

		db.execSQL(query);

		db.close();			   

	}
	
	public ArrayList<HashMap<String, String>> getAllClasses() {
		
		ArrayList<HashMap<String, String>> classesArrayList = new ArrayList<HashMap<String, String>>();
		
		String query = "SELECT * FROM classes ORDER BY className";
		
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(query, null);
		
		if(cursor.moveToFirst()) {
			
			do {
				
				HashMap<String, String> classesMap = new HashMap<String, String>();
				
				classesMap.put("classId", cursor.getString(0));
				classesMap.put("className", cursor.getString(1));
				classesMap.put("classType", cursor.getString(2));
				classesMap.put("classNumber", cursor.getString(3));
				classesMap.put("classSection", cursor.getString(4));
				classesMap.put("classYear", cursor.getString(5));
				
				classesArrayList.add(classesMap);
				
			} while(cursor.moveToNext());
			
		}
		
		return classesArrayList;
		
	}
	
}


