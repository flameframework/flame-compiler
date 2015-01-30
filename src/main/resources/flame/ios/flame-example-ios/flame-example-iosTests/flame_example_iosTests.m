<#-- TODO remove necessity to add &output tag here -->
<&output file="flame_example_iosTests.m" overwrite="false">
//
//  flame_example_iosTests.m
//  flame-example-iosTests
//
//  Created by Michel Vollebregt on 27-01-15.
//  Copyright (c) 2015 ORGANIZATIONNAME. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <XCTest/XCTest.h>

@interface flame_example_iosTests : XCTestCase

@end

@implementation flame_example_iosTests

- (void)setUp {
    [super setUp];
    // Put setup code here. This method is called before the invocation of each test method in the class.
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void)testExample {
    // This is an example of a functional test case.
    XCTAssert(YES, @"Pass");
}

- (void)testPerformanceExample {
    // This is an example of a performance test case.
    [self measureBlock:^{
        // Put the code you want to measure the time of here.
    }];
}

@end
</&output>