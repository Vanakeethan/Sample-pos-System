@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Transactional
    public Staff createStaff(Staff staff) {
        
        return staffRepository.save(staff);
    }

    @Transactional
    public Staff updateStaff(Long staffId, Staff updatedStaff) {
        // Find the staff member by ID
        Optional<Staff> optionalStaff = staffRepository.findById(staffId);
        if (optionalStaff.isPresent()) {
            Staff staff = optionalStaff.get();
            // Update the staff member's details
            staff.setName(updatedStaff.getName());
            staff.setRole(updatedStaff.getRole());
            // Save the updated staff member to the database
            return staffRepository.save(staff);
        } else {
            throw new IllegalArgumentException("Staff member not found with ID: " + staffId);
        }
    }

    @Transactional
    public void deleteStaff(Long staffId) {
        // Find the staff member by ID
        Optional<Staff> optionalStaff = staffRepository.findById(staffId);
        if (optionalStaff.isPresent()) {
            // Delete the staff member from the database
            staffRepository.deleteById(staffId);
        } else {
            throw new IllegalArgumentException("Staff member not found with ID: " + staffId);
        }
    }

    public List<Staff> getAllStaff() {
        // Retrieve all staff members from the database
        return staffRepository.findAll();
    }

    public Optional<Staff> getStaffById(Long staffId) {
        // Retrieve a staff member by ID from the database
        return staffRepository.findById(staffId);
    }

    // Add more business logic methods as needed
}
