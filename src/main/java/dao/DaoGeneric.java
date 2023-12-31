package dao;

import org.example.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class DaoGeneric<E> /*Entidade*/ {

    private EntityManager entityManager = HibernateUtil.getEntityManager();

    public void salvar(E entidade){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entidade);
        transaction.commit();
    }

    public  E updateMerge (E entidade){ // salva ou atualiza
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        E entidadeSalva = entityManager.merge(entidade);
        transaction.commit();

        return entidadeSalva;
    }

    public E pesquisar(E entidade){

        Object id = HibernateUtil.getPrimaryKey(entidade);
        E e = (E) entityManager.find(entidade.getClass(), id);

        return e;
    }

    public E pesquisar(Long id, Class<E> entidade){

        E e = (E) entityManager.find(entidade, id);

        return e;
    }

    public void deletarPorId(E entidade){
        Object id = HibernateUtil.getPrimaryKey(entidade);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.createNativeQuery("delete from " + entidade.getClass().getSimpleName().toLowerCase() +
                " where id = " + id).executeUpdate(); // faz delete

        transaction.commit(); //grava alteração
    }

    public List<E> listar(Class<E> entidade){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        List<E> lista = entityManager.createQuery("from " + entidade.getName()).getResultList();
        transaction.commit();

        return lista;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
