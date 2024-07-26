package com.devsu.api.cuentamovimiento.service.implement;

import com.devsu.api.cuentamovimiento.excepcion.Error;
import com.devsu.api.cuentamovimiento.manageObject.ProcessTransaction;
import com.devsu.api.cuentamovimiento.model.dto.AccountDto;
import com.devsu.api.cuentamovimiento.model.dto.TransactionDto;
import com.devsu.api.cuentamovimiento.model.entity.Account;
import com.devsu.api.cuentamovimiento.model.entity.Transaction;
import com.devsu.api.cuentamovimiento.repository.TransactionRepository;
import com.devsu.api.cuentamovimiento.service.TransactionService;
import com.devsu.api.cuentamovimiento.util.TransactionType;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionServiceImp implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountServiceImp accountServiceImp;

    private ModelMapper modelMapper=new ModelMapper();
    private ProcessTransaction processTransaction=new ProcessTransaction();


    @Override
    public TransactionDto createTransaction(TransactionDto transactionDTO) {
        if (transactionRepository.findById(transactionDTO.getId()).isPresent())
            throw new Error("987");
        else{
            Transaction transaction = modelMapper.map(transactionDTO,Transaction.class);
            transaction = registerTransaction(transaction);
            return modelMapper.map(transactionRepository.save(transaction),TransactionDto.class);
        }
    }

    @Override
    public List<TransactionDto> listallTransaction() {
        return transactionRepository.findAll().stream().map(
                (account)-> modelMapper.map(account,TransactionDto.class)
        ).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> listallTransactionByAccount(String numCuenta) {
        return transactionRepository.findAllByCuentaNumeroCuentaOrderByIdDesc(numCuenta).stream().map(
                (account)-> modelMapper.map(account,TransactionDto.class)
        ).collect(Collectors.toList());
    }
    @Override
    public List<TransactionDto> listallTransactionByDate(LocalDate startDate, LocalDate endDate, String cuenta) {
        return transactionRepository.findTransactionsByFechaAndNumeroCuenta(startDate, endDate, cuenta).stream().map(
                (account)-> modelMapper.map(account,TransactionDto.class)
        ).collect(Collectors.toList());
        //return null;
    }

    @Override
    public TransactionDto retrieveTransaction(Long id) {
        return transactionRepository.findById(id)
                .map(cuenta -> {
                    return modelMapper.map(cuenta,TransactionDto.class);
                })
                .orElseThrow(()-> new Error("986"));
    }

    @Override
    public Optional<TransactionDto> retrieveEndTransaction(String numCuenta) {
        return transactionRepository.findFirstByCuentaNumeroCuentaOrderByIdDesc(numCuenta)
                .map(cuenta -> {
                    return modelMapper.map(cuenta,TransactionDto.class);
                });
    }


    @Override
    public TransactionDto updateTransaction(TransactionDto transactionDTO) {
        return transactionRepository.findById(transactionDTO.getId())
                .map(trans -> {
                    Transaction Transaction = modelMapper.map(transactionDTO,Transaction.class);
                    return modelMapper.map(transactionRepository.save(Transaction),TransactionDto.class);
                })
                .orElseThrow(()-> new Error("986"));
    }

    @Override
    public void deleteTransaction(Long id) {
        if (!transactionRepository.findById(id).isPresent())
            throw new Error("986");
        else transactionRepository.deleteById(id);
    }

    public Transaction registerTransaction(Transaction transaction){
        log.info("TIPO MOVIMIENTO: {}", transaction.getTipoMovimiento());
        if (!transaction.getTipoMovimiento().equalsIgnoreCase(TransactionType.DEPOSITO.toString())&&
                !transaction.getTipoMovimiento().equalsIgnoreCase(TransactionType.RETIRO.toString())){
            throw new Error("992");
        }
        if (transaction.getTipoMovimiento().equalsIgnoreCase(TransactionType.DEPOSITO.toString())&&transaction.getValor()<0){
            throw new Error("998");
        }
        if (transaction.getTipoMovimiento().equalsIgnoreCase(TransactionType.RETIRO.toString())&&transaction.getValor()>0){
            throw new Error("995");
        }
        AccountDto account= accountServiceImp.retrieveAccountActive(transaction.getCuenta().getNumeroCuenta());
        transaction.setCuenta(modelMapper.map(account, Account.class));
        Optional<TransactionDto> transactionDto = retrieveEndTransaction(account.getNumeroCuenta());
        if (!transactionDto.isPresent()){
            transactionDto= Optional.of(new TransactionDto());
            transactionDto.get().setSaldo(0);
        }
        transaction.setSaldo(processTransaction.calculeTotal(transactionDto.get().getSaldo(),transaction.getValor()));
        return transaction;
    }
}
