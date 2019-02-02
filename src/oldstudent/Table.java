
package oldstudent;


public class Table {
  String id, sname,fname,dob,contact,house,email,course,occupation,location,date_saved;

    public Table(String id, String sname, String fname, String dob, String contact, String house, String email, String course, String occupation, String location, String date_saved) {
        this.id = id;
        this.sname = sname;
        this.fname = fname;
        this.dob = dob;
        this.contact = contact;
        this.house = house;
        this.email = email;
        this.course = course;
        this.occupation = occupation;
        this.location = location;
        this.date_saved = date_saved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate_saved() {
        return date_saved;
    }

    public void setDate_saved(String date_saved) {
        this.date_saved = date_saved;
    }

  
}
