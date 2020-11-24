package LibraryApp.Models;

public class Admin extends User {

    public Admin(String name, Library library, String password) {
        super(name, library, password);
        this.isAdmin = true;
        this.isMember = true;
        this.isStaff = true;
        this.library = library;
        double num = (Math.random() * ((999-1) + 1)) + 1;
        this.userID = "adm" + "3" + Double.toString(num) + name.substring(0,1);
        this.maxBooksAllowed = 30;
    }

//    public void checkOutBook(User reader, PhysicalBook book) { //will need to make this specifically a physical book
//        if (reader.isMember && reader.getLibrary() == this.getLibrary()) {
//            if(book.getLibrary() == this.getLibrary()) {
//                reader.getBooksOnLoan().add(book);
//                book.changeLoanStatus(this.getUserID());
//                book.decrementCopies(1);
//                book.incrementTimesRead();
//            } else {
//                System.out.println("the book is not in this library");
//            }
//        } else {
//            System.out.println("The reader is not a member of this library");
//        }
//    }
//
//    public void returnBook(User reader, PhysicalBook book){
//        if (reader.getBooksOnLoan().contains(book)  && reader.getLibrary() == this.getLibrary()) {
//            if (book.getLibrary()== this.getLibrary()){
//                reader.getBooksOnLoan().remove(book);
//                book.changeLoanStatus(this.getUserID());
//                book.incrementCopies(1);
//                book.setDueDate();
//            } else {
//                System.out.println("The book doesn't belong to this library");
//            }
//        } else {
//            System.out.println("The reader cannot return the book to this library");
//        }
//    }

    public void addStudent(){}

    public void removeStudent(){}
//    public void checkDamaged() {}
//    public void setDamaged(){
//      book.setDamaged(true)}
//    public void changeDueDate(Book) {}
//    public void addBooktoLibrary() {}

//    public void removeBookFromLibrary(Book book) {
//      if (book.getDamaged() = true)}
//    public void chargeFees() {}
//    public void notifyUser() {}
}
