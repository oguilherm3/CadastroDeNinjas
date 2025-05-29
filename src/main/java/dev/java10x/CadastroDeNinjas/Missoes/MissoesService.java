package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.Controller.NinjaModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;

    MissoesService(MissoesRepository missoesRepository){
        this.missoesRepository = missoesRepository;
    }

    public List<MissoesModel> listarMissoes(){
        return missoesRepository.findAll();
    }

    public Optional<MissoesModel> listarMissaoPorId(Long id){
        return missoesRepository.findById(id);
    }

    public MissoesModel criarMissao(MissoesModel missao){
        return missoesRepository.save(missao);
    }

    public void deletarMissaoPorID(Long id){
        missoesRepository.deleteById(id);
    }

    public MissoesModel alterarMissaoPorId(Long id, MissoesModel missaoToUpdate){
        return missoesRepository.findById(id).map(missao -> {
            missao.setNome(missaoToUpdate.getNome());
            missao.setDificuldade(missaoToUpdate.getDificuldade());
            return missoesRepository.save(missao);
        }).orElseThrow();
    }


}
