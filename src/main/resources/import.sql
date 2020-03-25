INSERT INTO users VALUES (1,'t','ADMIN','test');

INSERT INTO positionInReference (positionInReference) VALUES ('5''-promotor'), ('5''-untranslated region'), ('3''-untranslated region'), ('5''-exon'), ('3''-exon'), ('5''-intron'), ('3''-intron'), ('5''-enhancer'), ('5''-up'), ('3''-dw')
INSERT INTO purificationMethod (purificationMethod) VALUES ('Desalted'), ('Cartridge'), ('HPLC'), ('HPLC X'), ('PAGE')

INSERT INTO primers (name, sequence, orientation, length, freezer, drawer, box, positionInReference_id, Tm, OptimalTOfAnnealing, purificationMethod_id, amountAvailable, amountAvailableUnit, date, lengthOfAmplicone, storingT, GCPercent) VALUES ('testname', 'testsequence', 1, 42, 'freezer123', 93, 'box64', (SELECT id FROM positionInReference WHERE positionInReference = '5''-intron'), 24, 53, (SELECT id FROM purificationMethod WHERE purificationMethod = 'Cartridge'), 573, 1, '12-31-2019', 4251, 324.2, 32.9)

