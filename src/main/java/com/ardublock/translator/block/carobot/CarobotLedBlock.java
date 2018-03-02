package com.ardublock.translator.block.carobot;

import com.ardublock.translator.Translator;

public class CarobotLedBlock extends AbstractCarobotWriteDigitalBlock {
  public CarobotLedBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix,
      String label) {
    super(blockId, translator, codePrefix, codeSuffix, label);
  }

}
