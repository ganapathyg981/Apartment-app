package application;
import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Bill {
	
	 private final StringProperty RoomNo;
	    private final StringProperty Name;
	    private final StringProperty Rent;
	    private final StringProperty Maintanance;
	    private final StringProperty Eb;
	    private final StringProperty Sewage;
	    
	    private final StringProperty total;
	    private final StringProperty Timing;
	    
	   

		public StringProperty getRoomNo() {
			return RoomNo;
		}

		public StringProperty getName() {
			return Name;
		}

		public StringProperty getRent() {
			return Rent;
		}

		public StringProperty getMaintanance() {
			return Maintanance;
		}

		public StringProperty getEb() {
			return Eb;
		}

		public StringProperty getSewage() {
			return Sewage;
		}

		public StringProperty getTotal() {
			return total;
		}

		public StringProperty getTiming() {
			return Timing;
		}

		public Bill(String RoomNo,String Name,String Rent,String Maintanance,
				String Eb,String Sewage,String total,String Timing) {
			this.RoomNo = new SimpleStringProperty(RoomNo);
			this.Name = new SimpleStringProperty(Name);
			this.Rent = new SimpleStringProperty(Rent);
			this.Maintanance = new SimpleStringProperty(Maintanance);
			this.Eb = new SimpleStringProperty(Eb);
			this.Sewage = new SimpleStringProperty(Sewage);
			this.total = new SimpleStringProperty(total);
			this.Timing = new SimpleStringProperty(Timing);
		}
	   
}
