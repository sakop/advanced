package spring.transaction.dao;

public interface PersonDao {
    public void insertStudent(String name,int age,String course,int score);

    public void updateStudent(String name,int age);

    public void selectStudent(String where,String name);
}
