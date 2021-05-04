package uz.pdp.appbancomat.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.appbancomat.entity.Bancomat;
import uz.pdp.appbancomat.entity.Role;
import uz.pdp.appbancomat.entity.User;
import uz.pdp.appbancomat.payload.ApiResponse;
import uz.pdp.appbancomat.payload.BancomatDto;
import uz.pdp.appbancomat.repository.BancomatRepository;

import java.util.Set;

@Service
public class BancomatService {

    final BancomatRepository bancomatRepository;

    public BancomatService(BancomatRepository bancomatRepository) {
        this.bancomatRepository = bancomatRepository;
    }

    public ApiResponse add(BancomatDto bancomatDto) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getRole().equalsIgnoreCase("MANAGER")) {

                Bancomat bancomat = new Bancomat(
                        bancomatDto.getMoneyAmount(),
                        bancomatDto.getStreet(),
                        bancomatDto.getRegion(),
                        bancomatDto.getCardType()

                );
                bancomatRepository.save(bancomat);
                return new ApiResponse("Bancomat created", true);
            }

        }

        return new ApiResponse("Only MANAGER can creat bancomat", false);

    }
}
