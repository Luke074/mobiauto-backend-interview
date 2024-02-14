package com.mobiauto.lucas.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.mobiauto.lucas.domain.Oportunidades.OportunidadeConcluida;
import com.mobiauto.lucas.domain.Oportunidades.Oportunidades;
import com.mobiauto.lucas.domain.Oportunidades.OportunidadesRepository;
import com.mobiauto.lucas.domain.Oportunidades.OportunidadesRequest;
import com.mobiauto.lucas.domain.Oportunidades.StatusOportunidade;
import com.mobiauto.lucas.domain.Usuarios.AdminRequest;
import com.mobiauto.lucas.domain.Usuarios.Usuarios;
import com.mobiauto.lucas.domain.Usuarios.UsuariosRepository;

import jakarta.validation.Valid;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadesController {

    @Autowired
    private OportunidadesRepository oportunidadesRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @GetMapping
    public ResponseEntity<Object> getAllOportunidades() {
        List<Oportunidades> oportunidades = oportunidadesRepository.findAll();
        if (oportunidades.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma oportunidade encontrada.");
        }
        List<Map<String, String>> oportunidadeReturn = new ArrayList<>();
        for (Oportunidades oportunidade : oportunidades) {
            Map<String, String> oportunidadeInfo = new HashMap<>();

            oportunidadeInfo.put("oportunidade_id", String.valueOf(oportunidade.getId()));
            oportunidadeInfo.put("data_aplicacao", String.valueOf(oportunidade.getData_aplicacao()));
            oportunidadeInfo.put("data_conclusao", String.valueOf(oportunidade.getData_conclusao()));
            oportunidadeInfo.put("status_oportunidade", String.valueOf(oportunidade.getStatus_oportunidade()));
            oportunidadeInfo.put("nome_cliente", oportunidade.getNome_cliente());
            oportunidadeInfo.put("email_cliente", oportunidade.getEmail_cliente());
            oportunidadeInfo.put("telefone_cliente", String.valueOf(oportunidade.getTelefone_cliente().replaceAll(
                    "(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3")));

            Optional<Usuarios> usuarioOptional = usuariosRepository.findById(oportunidade.getUsuario_id());
            if (usuarioOptional.isPresent()) {
                Usuarios usuario = usuariosRepository.getReferenceById(oportunidade.getUsuario_id());

                oportunidadeInfo.put("nome_usuario", usuario.getNome());
                oportunidadeInfo.put("email_usuario", usuario.getEmail());
            }

            oportunidadeReturn.add(oportunidadeInfo);
        }
        return ResponseEntity.status(HttpStatus.OK).body(oportunidadeReturn);
    }

    @PostMapping
    public ResponseEntity<String> createdOportunidade(@RequestBody @Valid OportunidadesRequest data) {
        try {
            Oportunidades newOportunidade = new Oportunidades();

            newOportunidade.setStatus_oportunidade(StatusOportunidade.NOVO);
            newOportunidade.setData_aplicacao(data.data_aplicacao());
            newOportunidade.setUsuario_id(data.usuario_id());
            newOportunidade.setVeiculo_id(data.veiculo_id());

            newOportunidade.setNome_cliente(data.nome_cliente());
            newOportunidade.setEmail_cliente(data.email_cliente());
            newOportunidade.setTelefone_cliente(data.telefone_cliente().replaceAll("\\D", ""));

            oportunidadesRepository.save(newOportunidade);
            return ResponseEntity.status(HttpStatus.CREATED).body("Oportunidade cadastrada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar oportunidade: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> updateOportunidade(
            @RequestBody @Validated(OportunidadeConcluida.class) OportunidadesRequest data) {
        Optional<Oportunidades> optionOportunidades = oportunidadesRepository.findById(data.id());

        if (optionOportunidades.isPresent()) {
            try {

                Oportunidades oportunidade = oportunidadesRepository.getReferenceById(data.id());

                oportunidade.setStatus_oportunidade(data.status_oportunidade());

                Date dataAtual = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dataConclusao = data.data_conclusao() != null ? data.data_conclusao()
                        : formatter.parse(formatter.format(dataAtual));
                // oportunidade.setData_conclusao(dataConclusao);

                oportunidade.setRevenda_id(data.revenda_id());

                Long veiculo_id = data.veiculo_id() != null ? data.veiculo_id() : oportunidade.getVeiculo_id();
                oportunidade.setVeiculo_id(veiculo_id);

                String emailAtualizado = data.email_cliente() != null ? data.email_cliente()
                        : oportunidade.getEmail_cliente();
                oportunidade.setEmail_cliente(emailAtualizado);

                String nomeAtualizado = data.nome_cliente() != null ? data.nome_cliente()
                        : oportunidade.getNome_cliente();
                oportunidade.setNome_cliente(nomeAtualizado);

                String telefoneAtualizado = data.telefone_cliente() != null
                        ? data.telefone_cliente().replaceAll("\\D", "")
                        : oportunidade.getTelefone_cliente();
                oportunidade.setTelefone_cliente(telefoneAtualizado);

                oportunidadesRepository.save(oportunidade);

                return ResponseEntity.status(HttpStatus.OK).body("Oportunidade atualizada com sucesso!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao atualizar oportunidade: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro no servidor, aguarde alguns instantes.");
        }

    }
}
