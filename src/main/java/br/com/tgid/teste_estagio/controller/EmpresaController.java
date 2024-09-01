package br.com.tgid.teste_estagio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.tgid.teste_estagio.model.entities.Empresa;
import br.com.tgid.teste_estagio.model.repositories.EmpresaRepository;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaController(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @PostMapping
    public ResponseEntity<Empresa> criarEmpresa(
        @RequestParam String nome, 
        @RequestParam String cnpj, 
        @RequestParam Double saldo) {
        
        Empresa empresa = new Empresa();
        empresa.setNome(nome);
        empresa.setCnpj(cnpj);
        empresa.setSaldo(saldo);

        Empresa novaEmpresa = empresaRepository.save(empresa);
        return ResponseEntity.ok(novaEmpresa);
    }
    
    @GetMapping("/{cnpj}")
    public ResponseEntity<String> encontrarNomeDaEmpresaPorCnpj(@PathVariable String cnpj) {
        Empresa empresa = empresaRepository.findByCnpj(cnpj);
        if (empresa != null) {
            return ResponseEntity.ok(empresa.getNome());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}