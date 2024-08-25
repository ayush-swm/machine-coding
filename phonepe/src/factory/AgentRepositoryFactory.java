package factory;

import repository.AgentRepositoryInterface;
import repository.impl.AgentRepositoryImpl;

public class AgentRepositoryFactory {
    public static AgentRepositoryInterface getAgentRepository() {
        return AgentRepositoryImpl.getInstance();
    }
}
