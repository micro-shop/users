package cz.microshop.users.controller;

import cz.microshop.users.model.Account;
import cz.microshop.users.model.User;
import cz.microshop.users.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UsersController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<HttpStatus> deleteAll()   {
        usersService.deleteAll();
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ArrayList<User>> findAll()   {
        return new ResponseEntity<ArrayList<User>>((ArrayList<User>) usersService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<ArrayList<User>> create(@RequestBody ArrayList<User> userList)   {
        return new ResponseEntity<ArrayList<User>>((ArrayList<User>) usersService.create(userList), HttpStatus.OK);

    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<HttpStatus> authenticateUser(@RequestBody User user) {
        if(usersService.authenticateCustomer(user))
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        else
            return new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<Account> login(@Param("username") String username, @Param("password") String password) {
        User u = new User();
        u.setEmail(username);
        u.setPassword(password);
        //ResponseEntity<HttpStatus> httpStatusResponseEntity = authenticateUser(u);
        Account a = new Account();
        a.setPassword(password);
        a.setName(username);
        return new ResponseEntity<Account>(a, HttpStatus.OK);

    }

}
