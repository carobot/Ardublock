package com.ardublock.translator.block.carobot;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class CarobotMotorSpeedBlock extends AbstractCarobotWriteAnalogBlock {
  public CarobotMotorSpeedBlock(Long blockId, Translator translator, String codePrefix,
      String codeSuffix, String label) {
    super(blockId, translator, codePrefix, codeSuffix, label);
  }

  public String toCode() throws SocketNullException, SubroutineNotDeclaredException {
    translator.addHeaderFile("CAROBOT_SwissCHEESE.h");
    TranslatorBlock MotorBlock = this.getRequiredTranslatorBlockAtSocket(0);
    TranslatorBlock SpeedBlock = this.getRequiredTranslatorBlockAtSocket(1);
    String MotorNumber = MotorBlock.toCode();
    String Number = MotorNumber.substring(1, 2);
    String Speed = SpeedBlock.toCode();
    translator.addDefinitionCommand("SCMotor motor" + Number + "(" + MotorNumber + ");");

    return "motor" + Number + ".speed(" + Speed + ");\n";
  }

}
