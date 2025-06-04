package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.Controller.NinjaModel;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;
    private MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    public List<MissoesDTO> listarMissoes(){
        return missoesRepository.findAll()
                .stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }

    public MissoesDTO listarMissaoPorId(Long id){
        return missoesRepository.findById(id)
                .map(missoesMapper::map).orElse(null);
    }

    public MissoesDTO criarMissao(MissoesDTO missaoDTO){
        MissoesModel missaoModel = missoesMapper.map(missaoDTO);
        missaoModel = missoesRepository.save(missaoModel);
        missaoDTO = missoesMapper.map(missaoModel);

        return missaoDTO;
    }

    public void deletarMissaoPorID(Long id){
        missoesRepository.deleteById(id);
    }

    public MissoesDTO alterarMissaoPorId(Long id, MissoesDTO missaoToUpdate) {

        Optional<MissoesModel> missaoExistente = missoesRepository.findById(id);
        if (missaoExistente.isPresent()) {
            MissoesModel missaoAtualizada = missoesMapper.map(missaoToUpdate);
            missaoAtualizada.setId(id);

            MissoesModel missaoSalva = missoesRepository.save(missaoAtualizada);

            return missoesMapper.map(missaoSalva);
        }

        return null;
    }
}
