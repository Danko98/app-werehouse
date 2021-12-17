package uz.pdp.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Currency;
import uz.pdp.appwerehouse.repository.CurrencyRepository;

import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;


    public Result addCurrency(Currency currencyDto){
        if (currencyRepository.existsByName(currencyDto.getName())){
            return new Result("Bunday valyuta mavjud",false);
        }
        Currency currency = new Currency();
        currency.setName(currencyDto.getName());
        currencyRepository.save(currency);
        return new Result("Valyuta saqlandi",true);
    }

    public Result editCurrency(Integer id, Currency currencyDto, boolean active){

        //
        if (currencyRepository.existsById(id)){
            return new Result("Bunday valyuta ro'yxatdan topilmadi",false);
        }

        //
        if (currencyRepository.existsByName(currencyDto.getName())){
            return new Result("Siz qo'shmoqchi bo'lgan valyuta ro'yxatda mavjud",false);
        }
        
        //
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        Currency currency = optionalCurrency.get();
        currency.setName(currencyDto.getName());
        currency.setActive(active);
        currencyRepository.save(currency);

        return new Result("Valyuta tahrirlandi",true);
    }

    public Page<Currency> getCurrencyPage(int page){
        Pageable pageable = PageRequest.of(page,10);
        return currencyRepository.findAll(pageable);
    }

    public Result getCurrencyById(Integer id){
        if (!currencyRepository.existsById(id)){
            return new Result("Bunday valyuta ro'yxatda mavjud emas",false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        return new Result(optionalCurrency.get());
    }

    public Result deletCurrency(Integer id){
        if (!currencyRepository.existsById(id)){
            return new Result("Bunday valyuta ro'yxatda mavjud emas",false);
        }
        currencyRepository.deleteById(id);
        return new Result("Valyuta ro'yxatdan o'chirildi",true);
    }





}






