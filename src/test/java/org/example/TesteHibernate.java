package org.example;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;
import org.junit.Test;

import java.util.List;

public class TesteHibernate {

    @Test
    public void testeHibernateUtil() {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

        UsuarioPessoa pessoa = new UsuarioPessoa();

        pessoa.setIdade(32);
        pessoa.setLogin("teste");
        pessoa.setNome("Ichigo");
        pessoa.setSobrenome("Bondade");
        pessoa.setEmail("teste@123.com");
        pessoa.setSenha("123");

        daoGeneric.salvar(pessoa);

    }


    @Test
    public void testeBuscar() {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        UsuarioPessoa pessoa = new UsuarioPessoa();
        pessoa.setId(1L);

        pessoa = daoGeneric.pesquisar(pessoa);

        System.out.println(pessoa);

    }

    @Test
    public void testeBuscar2() {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

        UsuarioPessoa pessoa = daoGeneric.pesquisar(4L, UsuarioPessoa.class);

        System.out.println(pessoa);

    }

    @Test
    public void testeUpdate() {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        UsuarioPessoa pessoa = daoGeneric.pesquisar(1L, UsuarioPessoa.class);

        pessoa.setIdade(99);
        pessoa.setNome("Nome atualizado Hibernate");
        pessoa.setSenha("teeeste3");

        pessoa = daoGeneric.updateMerge(pessoa);

        System.out.println(pessoa);

    }

    @Test
    public void testeDelete() {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        UsuarioPessoa pessoa = daoGeneric.pesquisar(4L, UsuarioPessoa.class);

        daoGeneric.deletarPorId(pessoa);

    }

    @Test
    public void testeConsultar() {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

        List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);

        for (UsuarioPessoa usuarioPessoa : list) {
            System.out.println(usuarioPessoa);
            System.out.println("______________________________________________");

        }


    }

    @Test
    public void testeQueryList() {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

        List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa where nome = 'Luna' or nome = 'Ichigo' ").getResultList();

        for (UsuarioPessoa usuarioPessoa : list) {
            System.out.println(usuarioPessoa);
        }
    }

    @Test
    public void testeQueryListMaxResult() {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

        List<UsuarioPessoa> list = daoGeneric.getEntityManager().
                createQuery(" from UsuarioPessoa order by id ")
                .setMaxResults(1)
                .getResultList();

        for (UsuarioPessoa usuarioPessoa : list) {
            System.out.println(usuarioPessoa);
        }
    }

    @Test
    public void testeQueryListParameter() {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

        List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa where nome = :nome or :sobrenome = sobrenome ")
                .setParameter("nome", "Luna")
                .setParameter("sobrenome", "Bondade")
                .getResultList();

        for (UsuarioPessoa usuarioPessoa : list) {
            System.out.println(usuarioPessoa);
        }
    }

    @Test
    public void testeQuerySomaIdade() {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        Long somaIdade = (Long) daoGeneric.getEntityManager().createQuery("select sum(u.idade) from UsuarioPessoa u ").getSingleResult();
        System.out.println("Soma de todas as idades é -->" + somaIdade);
    }

    @Test
    public void testeNamedQuery() {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.todos").getResultList();

        for (UsuarioPessoa usuarioPessoa : list) {
            System.out.println(usuarioPessoa);
        }

    }

    @Test
    public void testeNamedQuery2() {
        DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.buscaPorNome")
                .setParameter("nome", "Atena")
                .getResultList();

        for (UsuarioPessoa usuarioPessoa : list) {
            System.out.println(usuarioPessoa);
        }

    }
    @Test
    public void testeGravaTelefone(){
        DaoGeneric daoGeneric = new DaoGeneric();
        UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(7L, UsuarioPessoa.class);

        TelefoneUser telefoneUser = new TelefoneUser();

        telefoneUser.setTipo("Casa");
        telefoneUser.setNumero("8888-4444");
        telefoneUser.setUsuarioPessoa(pessoa);

        daoGeneric.salvar(telefoneUser);

    }

    @Test
    public void testeConsultaTelefones(){
        DaoGeneric daoGeneric = new DaoGeneric();
        UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(8L, UsuarioPessoa.class);

        for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
            System.out.println(fone.getNumero());
            System.out.println(fone.getTipo());
            System.out.println(fone.getUsuarioPessoa().getNome());
            System.out.println("__________________________________________");
        }



    }


}
