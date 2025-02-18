public class ApartmentBooking {
    private String id;
    private String apartmentId;
    private String userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double securityDepositPaid;
    private PaymentStatus paymentStatus;
    private BookingStatus bookingStatus;
    
    public enum PaymentStatus {
        PENDING, PARTIAL, COMPLETED, REFUNDED
    }
    
    public enum BookingStatus {
        REQUESTED, APPROVED, REJECTED, CANCELLED
    }
    
    // Constructor and methods
} 