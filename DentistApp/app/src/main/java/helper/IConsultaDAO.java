package helper;

import java.util.List;

import model.consulta;
import model.user;

public interface IConsultaDAO {

    public boolean salvarConsulta(consulta consult );

    public boolean atualizarConsulta(consulta com);

    public List<consulta> buscar(String email);

    public boolean deletarConsulta(consulta cons);

}
