package com.merlin204.sw.entity.super_warden.ai;



import com.merlin204.avalon.entity.condition.TargetInGirdCondition;
import com.merlin204.sw.entity.super_warden.SWPatch;
import com.merlin204.sw.epicfight.gameassets.animations.SuperWardenAnimation;
import yesman.epicfight.world.entity.ai.goal.CombatBehaviors;
import yesman.epicfight.world.entity.ai.goal.CombatBehaviors.Behavior;
import yesman.epicfight.world.entity.ai.goal.CombatBehaviors.BehaviorSeries;
import yesman.epicfight.world.entity.ai.goal.CombatBehaviors.Builder;


public class SWCombatBehaviors {


    public static final Builder<SWPatch> TYPE1 = CombatBehaviors.<SWPatch>builder()


            .newBehaviorSeries(
                    BehaviorSeries.<SWPatch>builder().weight(20).cooldown(10).canBeInterrupted(false).looping(false)
                            .nextBehavior(Behavior.<SWPatch>builder().animationBehavior(SuperWardenAnimation.SW_ATK_1_1).predicate(new TargetInGirdCondition(
                                    new TargetInGirdCondition.Rectangle(1, 1, -1, 2)))
                            ).nextBehavior(Behavior.<SWPatch>builder().animationBehavior(SuperWardenAnimation.SW_ATK_1_2).predicate(new TargetInGirdCondition(
                                            new TargetInGirdCondition.Rectangle(1, 0, -1, 3)))))

            .newBehaviorSeries(
                    BehaviorSeries.<SWPatch>builder().weight(30).cooldown(10).canBeInterrupted(false).looping(false)
                            .nextBehavior(Behavior.<SWPatch>builder().animationBehavior(SuperWardenAnimation.SW_ATK_2_1).predicate(new TargetInGirdCondition(
                                    new TargetInGirdCondition.Rectangle(1, 0, -1, 2)))
                            ).nextBehavior(Behavior.<SWPatch>builder().animationBehavior(SuperWardenAnimation.SW_ATK_2_2).predicate(new TargetInGirdCondition(
                                            new TargetInGirdCondition.Rectangle(1, 0, -1, 3))))
                            .nextBehavior(Behavior.<SWPatch>builder().animationBehavior(SuperWardenAnimation.SW_ATK_2_3).predicate(new TargetInGirdCondition(
                                    new TargetInGirdCondition.Rectangle(1, 0, -1, 3))))
            )

            .newBehaviorSeries(
                    BehaviorSeries.<SWPatch>builder().weight(50).cooldown(10).canBeInterrupted(false).looping(false)
                            .nextBehavior(Behavior.<SWPatch>builder().animationBehavior(SuperWardenAnimation.SW_ATK_3_1).predicate(new TargetInGirdCondition(
                                    new TargetInGirdCondition.Rectangle(1, 1, -1, 2)))
                            ).nextBehavior(Behavior.<SWPatch>builder().animationBehavior(SuperWardenAnimation.SW_ATK_3_2).predicate(new TargetInGirdCondition(
                                            new TargetInGirdCondition.Rectangle(1, 0, -1, 3))))
                            .nextBehavior(Behavior.<SWPatch>builder().animationBehavior(SuperWardenAnimation.SW_ATK_3_3).predicate(new TargetInGirdCondition(
                                    new TargetInGirdCondition.Rectangle(1, 0, -1, 3))))
            )

            .newBehaviorSeries(
                    BehaviorSeries.<SWPatch>builder().weight(20).cooldown(10).canBeInterrupted(false).looping(false)
                            .nextBehavior(Behavior.<SWPatch>builder().animationBehavior(SuperWardenAnimation.SW_ATK_4_1).predicate(new TargetInGirdCondition(
                                    new TargetInGirdCondition.Rectangle(1, 0, -1, 2)))
                            ).nextBehavior(Behavior.<SWPatch>builder().animationBehavior(SuperWardenAnimation.SW_ATK_4_2).predicate(new TargetInGirdCondition(
                                    new TargetInGirdCondition.Rectangle(1, 0, -1, 3))))

            )

            .newBehaviorSeries(
                    BehaviorSeries.<SWPatch>builder().weight(50).cooldown(10).canBeInterrupted(false).looping(false)
                            .nextBehavior(Behavior.<SWPatch>builder().animationBehavior(SuperWardenAnimation.SW_ATK_SKILL_1).predicate(new TargetInGirdCondition(
                                    new TargetInGirdCondition.Rectangle(1, 6, -1, 9)))
                            )
            )

            .newBehaviorSeries(
                    BehaviorSeries.<SWPatch>builder().weight(50).cooldown(150).canBeInterrupted(false).looping(false)
                            .nextBehavior(Behavior.<SWPatch>builder().animationBehavior(SuperWardenAnimation.SW_ATK_SKILL_2).predicate(new TargetInGirdCondition(
                                    new TargetInGirdCondition.Rectangle(1, 1, -1, 6)))
                            )
            )

            .newBehaviorSeries(
                    BehaviorSeries.<SWPatch>builder().weight(1).cooldown(120).canBeInterrupted(false).looping(false)
                            .nextBehavior(Behavior.<SWPatch>builder().animationBehavior(SuperWardenAnimation.SW_SONICBOOM).withinDistance(5,30F))

            )


            ;










}
