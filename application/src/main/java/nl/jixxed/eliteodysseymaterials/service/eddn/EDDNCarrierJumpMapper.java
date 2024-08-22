package nl.jixxed.eliteodysseymaterials.service.eddn;

import nl.jixxed.eliteodysseymaterials.enums.Expansion;
import nl.jixxed.eliteodysseymaterials.schemas.eddn.approachsettlement.StationEconomy;
import nl.jixxed.eliteodysseymaterials.schemas.eddn.approachsettlement.SystemFaction;
import nl.jixxed.eliteodysseymaterials.schemas.eddn.carrierjump.*;
import nl.jixxed.eliteodysseymaterials.schemas.journal.CarrierJump.CarrierJump;

public class EDDNCarrierJumpMapper extends EDDNMapper {
    public static Message mapToEDDN(final CarrierJump carrierJump, final Expansion expansion) {
        return new Message.MessageBuilder()
                .withTimestamp(carrierJump.getTimestamp())
                .withEvent(carrierJump.getEvent())
                .withStarPos(carrierJump.getStarPos())
                .withStarSystem(carrierJump.getStarSystem())
                .withSystemAddress(carrierJump.getSystemAddress())
                .withHorizons(expansion.equals(Expansion.HORIZONS) || expansion.equals(Expansion.ODYSSEY))
                .withOdyssey(expansion.equals(Expansion.ODYSSEY))
                .withBodyID(carrierJump.getBodyID())
                .withBody(carrierJump.getBody())
                .withBodyType(carrierJump.getBodyType())
                .withMarketID(carrierJump.getMarketID())
                .withDocked(carrierJump.getDocked())
                .withTaxi(carrierJump.getTaxi().orElse(null))
                .withMulticrew(carrierJump.getMulticrew().orElse(null))
                .withPopulation(carrierJump.getPopulation())
                .withPowerplayState(carrierJump.getPowerplayState().orElse(null))
                .withPowers(mapToNullIfEmptyList(carrierJump.getPowers()).orElse(null))
                .withStationEconomies(carrierJump.getStationEconomies().map(stationEconomies -> stationEconomies.stream()
                                .map(stationEconomy -> new StationEconomy.StationEconomyBuilder()
                                        .withName(stationEconomy.getName())
                                        .withProportion(stationEconomy.getProportion())
                                        .build())
                                .toList()).orElse(null))
                .withStationEconomy(carrierJump.getStationEconomy())
                .withStationFaction(carrierJump.getStationFaction()
                        .map(faction -> new SystemFaction.SystemFactionBuilder()
                            .withName( faction.getName())
                            .build())
                        .orElse(null))
                .withStationGovernment(carrierJump.getStationGovernment())
                .withStationName(carrierJump.getStationName())
                .withStationServices(mapToNullIfEmptyList(carrierJump.getStationServices()).orElse(null))
                .withStationType(carrierJump.getStationType())
                .withSystemEconomy(carrierJump.getSystemEconomy())
                .withSystemAllegiance(carrierJump.getSystemAllegiance())
                .withSystemFaction(carrierJump.getSystemFaction()
                        .map(faction -> new SystemFaction.SystemFactionBuilder()
                                .withName(faction.getName())
                                .withFactionState(faction.getFactionState().orElse(null))
                                .build())
                        .orElse(null))
                .withSystemGovernment(carrierJump.getSystemGovernment())
                .withSystemSecondEconomy(carrierJump.getSystemSecondEconomy())
                .withSystemSecurity(carrierJump.getSystemSecurity())
                .withFactions(mapToNullIfEmptyList(carrierJump.getFactions())
                        .map(factions -> factions.stream()
                        .map(faction -> new Faction.FactionBuilder()
                                .withName(faction.getName())
                                .withFactionState(faction.getFactionState())
                                .withAllegiance(faction.getAllegiance())
                                .withGovernment(faction.getGovernment())
                                .withHappiness(faction.getHappiness())
                                .withInfluence(faction.getInfluence())
                                .withActiveStates(mapToNullIfEmptyList(faction.getActiveStates())
                                        .map(activeStates -> activeStates.stream()
                                                .map(activeState -> new ActiveState.ActiveStateBuilder()
                                                        .withState(activeState.getState())
                                                        .build())
                                                .toList())
                                        .orElse(null))
                                .withPendingStates(mapToNullIfEmptyList(faction.getPendingStates())
                                        .map(pendingStates -> pendingStates.stream()
                                                .map(pendingState -> new PendingState.PendingStateBuilder()
                                                        .withState(pendingState.getState())
                                                        .withTrend(pendingState.getTrend())
                                                        .build())
                                                .toList())
                                        .orElse(null))
                                .withRecoveringStates(mapToNullIfEmptyList(faction.getRecoveringStates())
                                        .map(recoveringStates -> recoveringStates.stream()
                                                .map(recoveringState -> new RecoveringState.RecoveringStateBuilder()
                                                        .withState(recoveringState.getState())
                                                        .withTrend(recoveringState.getTrend())
                                                        .build())
                                                .toList())
                                        .orElse(null))
                                .build())
                        .toList()).orElse(null))
                .withConflicts(mapToNullIfEmptyList(carrierJump.getConflicts())
                        .map(conflicts -> conflicts.stream()
                                .map(conflict -> new Conflict.ConflictBuilder()
                                        .withStatus(conflict.getStatus())
                                        .withWarType(conflict.getWarType())
                                        .withFaction1(new ConflictFaction.ConflictFactionBuilder()
                                                .withName(conflict.getFaction1().getName())
                                                .withStake(conflict.getFaction1().getStake())
                                                .withWonDays(conflict.getFaction1().getWonDays())
                                                .build())
                                        .withFaction2(new ConflictFaction.ConflictFactionBuilder()
                                                .withName(conflict.getFaction2().getName())
                                                .withStake(conflict.getFaction2().getStake())
                                                .withWonDays(conflict.getFaction2().getWonDays())
                                                .build())
                                        .build())
                                .toList())
                        .orElse(null))
                .withThargoidWar(carrierJump.getThargoidWar()
                        .map(thargoidWar -> new ThargoidWar.ThargoidWarBuilder()
                                .withCurrentState(thargoidWar.getCurrentState())
                                .withSuccessStateReached(thargoidWar.getSuccessStateReached())
                                .withEstimatedRemainingTime(thargoidWar.getEstimatedRemainingTime().orElse(null))
                                .withWarProgress(thargoidWar.getWarProgress().orElse(null))
                                .withRemainingPorts(thargoidWar.getRemainingPorts().orElse(null))
                                .withNextStateFailure(thargoidWar.getNextStateFailure().orElse(null))
                                .withNextStateSuccess(thargoidWar.getNextStateSuccess().orElse(null))
                        .build())
                        .orElse(null))
                .build();
    }
}
