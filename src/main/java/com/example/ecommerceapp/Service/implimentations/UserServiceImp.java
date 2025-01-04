package com.example.ecommerceapp.Service.implimentations;
import com.example.ecommerceapp.Entity.User;
import com.example.ecommerceapp.Exceptions.CustomerNotFoundException;
import com.example.ecommerceapp.Exceptions.UserException;
import com.example.ecommerceapp.Repository.UserRepository;
import com.example.ecommerceapp.Service.UserService;
import com.example.ecommerceapp.config.JwtProvider;
import com.example.ecommerceapp.requests.AddUserRequest;
import com.example.ecommerceapp.requests.UpdateCustomerRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private UserRepository customerRepo;

    private JwtProvider jwtProvider;

    public UserServiceImp(UserRepository customerRepo, JwtProvider jwtProvider) {
        this.customerRepo = customerRepo;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public List<User> getAllCustomer() {
        return customerRepo.findAll();
    }

    @Override
    public User getCustomerById(Long id) {

        return customerRepo.findById(id).orElseThrow(()->new
                CustomerNotFoundException("User by id: "+ id + " does not found."));
    }
    private Boolean existCustomerByEmail(String email){
        return customerRepo.existsUserByEmail(email);
    }
    @Override
    public User addCustomer(AddUserRequest request) {

        if(existCustomerByEmail(request.getEmail())){
            throw new IllegalArgumentException("Email already exists..!!");
        }

        User customer= new User();
        customer.setName(request.getName());
        customer.setSurname(request.getSurname());
        customer.setAddresses(request.getAddress());
        customer.setRole(request.getRole());
        customer.setAge(request.getAge());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        return customerRepo.save(customer);
    }

    @Transactional
    @Override
    public User updateCustomer(UpdateCustomerRequest request, Long id) {
        User newCustomer = getCustomerById(id);
        if(request.getAddress()!=null){
            newCustomer.setAddresses(request.getAddress());
        }
        if (request.getName() != null) {
            newCustomer.setName(request.getName());
        }
        if (request.getSurname() != null) {
            newCustomer.setSurname(request.getSurname());
        }
        if(request.getRole()!=null){
            newCustomer.setRole(request.getRole());
        }
        if(request.getAge()!= newCustomer.getAge() && request.getAge()>0) {
            newCustomer.setAge(request.getAge());
        }
        if (request.getEmail() != null) {
            newCustomer.setEmail(request.getEmail());
        }
        if (request.getPhoneNumber() != null) {
            newCustomer.setPhoneNumber(request.getPhoneNumber());
        }
        return customerRepo.save(newCustomer);
    }

    @Transactional
    public ResponseEntity<?> deleteCustomer(Long id) {
        if (!customerRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer with id " + id + " not found.");
        }
        customerRepo.deleteById(id);
        return ResponseEntity.ok("User deleted successfully.");
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {

        String email= jwtProvider.getEmailFromToken(jwt);
        User user= customerRepo.findByEmail(email);

        if(user==null){
            throw new UserException("user not found with email"+ email);
        }
        return user;
    }

    public UserDetails loadUserByEmail(String userName) throws UsernameNotFoundException {
        User user= customerRepo.getUserByEmail(userName);

        if(user==null){
            throw new UsernameNotFoundException("User not found with email: "+ userName);

        }
        List<GrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);

    }
}