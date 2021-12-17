package uz.pdp.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.User;
import uz.pdp.appwerehouse.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {



    @Autowired
    UserRepository userRepository;


    public Result addUser(User userDto){

        if (userRepository.existsByFirstNameAndLastNameAndPhoneNumber(userDto.getFirstName(),userDto.getLastName(),userDto.getPhoneNumber())){
            return new Result("Bu User mavjud",false);
        }

        String code = UUID.randomUUID().toString();

        User user = new User();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setActive(true);
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        user.setCode(code);

        return new Result("User saqlandi",true);

    }

    public Page<User> getUserPage(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return userRepository.findAll(pageable);
    }

    public Result getUserById(Integer id){
        if (userRepository.existsById(id)){
            return new Result("Bunday User topilmadi",false);
        }
        Optional<User> optionalUser = userRepository.findById(id);
        return new Result(optionalUser.get());
    }

    public Result editUser(Integer id,User userDto,boolean active){
        if (userRepository.existsById(id)){
            return new Result("Bunday user mavjud emas",false);
        }

        if (userRepository.existsByFirstNameAndLastNameAndPhoneNumber(userDto.getFirstName(),userDto.getLastName(),userDto.getPhoneNumber())){
            return new Result("Bunday user ro'yxatda mavjud",false);
        }

        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setActive(active);
        return new Result("User tahrirlandi",true);
    }

    public Result deleteUser(Integer id){
        if (userRepository.existsById(id)){
            return new Result("Bunday user topilmadi",false);
        }
        userRepository.deleteById(id);
        return new Result("User o'chirildi",true);
    }

}
