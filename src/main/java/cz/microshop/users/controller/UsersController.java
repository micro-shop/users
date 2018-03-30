package cz.microshop.users.controller;

import cz.microshop.users.model.User;
import cz.microshop.users.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UsersController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<HttpStatus> save(@RequestBody User user)   {
        usersService.save(user);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<HttpStatus> deleteAll()   {
        usersService.deleteAll();
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ArrayList<User>> findAll()   {
        return new ResponseEntity<ArrayList<User>>((ArrayList<User>) usersService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> find(@RequestParam String username)   {
        return new ResponseEntity<User>(usersService.findByUsername(username), HttpStatus.OK);
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
    public ResponseEntity<User> login(@Param("username") String username, @Param("password") String password) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        if (usersService.authenticateCustomer(u)) {
            return new ResponseEntity<User>(usersService.findByUsername(username), HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
        }
    }

}
